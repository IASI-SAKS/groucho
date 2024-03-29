# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#  
#  This file is part of the GROUCHO project.
#  
#  GROUCHO is free software: you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#  
#  GROUCHO is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  
#  You should have received a copy of the GNU General Public License
#  along with GROUCHO.  If not, see <https://www.gnu.org/licenses/>
# 
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

import os
import sys
import argparse
from collections import defaultdict
from pickle import dump, load

import xxhash



# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# MINWISEHASHING

def hash_family(i):
    def hash_member(x):
        return xxhash.xxh64(x, seed=37 * (2 * i + 1)).hexdigest()
    return hash_member


def state_minhashing(state, hash_functions):
    """INPUT
    (string)state: a string representing the state of an object
    (list(fun))hash_functions: list of hash_functions

    OUTPUT
    (list)state_signature: list of minhash values (signature)
    """
    n = len(hash_functions)
    state_shingles = state
    # initialized to max_value ('ffffffff') to correctly compute the min
    state_signature = ["ffffffff" for i in range(n)]
    for state_shingle in state_shingles:
        for i in range(n):
            state_hash = hash_functions[i](str(state_shingle))
            if state_hash < state_signature[i]:
                state_signature[i] = state_hash

    return state_signature


# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# LOCALITY SENSITIVE HASHING (LSH)

def LSH_candidates(bucket, signature, b, r, n):
    """INPUT
    (dict)bucket: key=band, val=dict(key=col_sig, val=set(IDs))
    (list)signature: minhash
    (int)b: number of bands
    (int)r: number of rows
    (int)n: number of hash functions (n = b*r)

    OUTPUT
    (set)candidates: set of possibly similar states"""
    assert(b * r == n)

    candidates = set()

    i = 0
    while i < n:  # for each band
        column = signature[i:i + r]
        #column_signature = xxhash.xxh64(str(column), seed=0).intdigest()
        aux_column_signature = xxhash.xxh64(str(column), seed=0).intdigest()

        #for tc_ID in bucket[i][column_signature]:
        for tc_ID in bucket[i][aux_column_signature]:
            candidates.add(tc_ID)

        i += r  # next band

    return candidates


def init_bucket(r, n):
    bucket = defaultdict(dict)
    i = 0
    while i < n:  # for each band
        bucket[i] = defaultdict(set)  # to catch collisions in each band
        i += r  # next band
    return bucket


def add_to_bucket(tc_ID, bucket, signature, b, r, n):
    i = 0
    while i < n:  # for each band
        column = signature[i:i + r]
        column_signature = xxhash.xxh64(str(column), seed=0).intdigest()

        bucket[i][column_signature].add(tc_ID)

        i += r  # next band

    
def get_signature(state, shingling=True):
    if shingling:
        # shingling
        state_ = state[:]
        state_shingles = set()
        for i in range(len(state_) - k + 1):
            state_shingles.add(xxhash.xxh64(state_[i:i + k], seed=0).intdigest())            
        sig = state_minhashing(set(state_shingles), hashes)
    else:
        state_ = state[:].split()
        sig = state_minhashing(set(state_), hashes)
    return sig


# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# AUX
def load_data():
    if os.path.exists(bucket_data_file):
        with open(bucket_data_file, "rb") as bucket_in:
            bucket = load(bucket_in)
        with open(countstates_data_file, "rb") as count_in:
            state_count = load(count_in)
    else:        
        bucket = init_bucket(r, n)
        state_count = 0
    return bucket, state_count

def save_data():
    # Saving 'bucket' and 'state_count'
    with open(bucket_data_file, "wb") as bucket_out:
        dump(bucket, bucket_out)    
    with open(countstates_data_file, "wb") as count_out:
        dump(state_count, count_out)

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# invivo_gov
def invivo_gov(state, query_only=False):
    run_invivo = 1
    state_signature = get_signature(state)  # computes the minhashing signature of the state representation
    sim_cand = LSH_candidates(bucket, state_signature, b, r, n)  # queries the LSH bucket with the signature just added

    if len(sim_cand) > 0:  # the newly added state is similar (above the defined threshold) to previously seen states
        run_invivo = 0
    
    if not query_only:
        add_to_bucket(state_count, bucket, state_signature, b, r, n)  # adds the state signature to the LSH bucket
    
    return run_invivo


# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# CONFIG

# FAST parameters
#k, n, r, b = 5, 100, 10, 10  # (1/b)^(1/r) -> (1/10)^(1/10) -> Sim threshold = 0.79
k, n, r, b = 5, 10, 5, 2  # (1/b)^(1/r) -> (1/2)^(1/5) -> Sim threshold = 0.87

bucket_data_file = "bucket.data"
hashes_data_file = "hashes.data"
countstates_data_file = "count.data"

hashes = [hash_family(i) for i in range(n)]



if __name__ == '__main__':
    bucket, state_count = load_data()    

    # Parsing Arguments
    parser = argparse.ArgumentParser(description='The LSH-based invivo governance decides whether or not to recommend an invivo session based on the similarity between the provided <state> and previously-seen states. The return code "0" means that an invivo session is *not* recommended, whereas the return code 1 means that an invivo session is recommended for the given <state>.')
    parser.add_argument('state', nargs=1, 
                        help='A string representation of the <state> to be queried or inserted into the LSH bucket.')
    parser.add_argument('-q', '--query-only', dest='query_only', action='store_true',
                        help='Reports if an invivo session would be recommended for a given <state> without adding it to the LSH bucket. 0 = invivo session *not* recommended. 1 = invivo session recommended.')
    args = parser.parse_args()

    state = args.state[0]
    query_only = args.query_only
    run_invivo = invivo_gov(state, query_only)
    if not query_only:
        state_count += 1
    
    print(run_invivo)
    
    save_data()
