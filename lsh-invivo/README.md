# LSH-based in-vivo testing governance

### Getting started

1. Clone the repository:
   - `git clone https://github.com/IASI-SAKS/groucho.git`
 
2. Install Python 3.5 or higher:
   - `sudo apt-get install python3.5`
   - `sudo apt-get install python3-pip`

3. Install the additional python packages required:
   - `pip install -r requirements.txt`

### Trying out the code

 - From the `lsh-invivo` directory, run `python py/lshinvivo.py <state_representation>` replacing `<state_representation>` with any string.

### Usage

``lshinvivo.py [-h] [-q] state``

The LSH-based invivo governance decides whether or not to recommend an invivo session based on the similarity between the provided ``<state>`` and previously-seen states. The return code "0" means that an invivo session is **not** recommended, whereas the return code 1 means that an invivo session is recommended for the given ``<state>``.

#### Arguments
* positional arguments:
  - ``state``: A string representation of the <state> to be queried or inserted into the LSH bucket
* optional arguments:
  - ``-h``, ``--help``: shows this help message and exit
  - ``-q``, ``--query-only``: reports if an invivo session would be recommended for a given ``<state>`` without adding it to the LSH bucket:
    * "0" means invivo session **not** recommended
    * "1" means invivo session recommended

