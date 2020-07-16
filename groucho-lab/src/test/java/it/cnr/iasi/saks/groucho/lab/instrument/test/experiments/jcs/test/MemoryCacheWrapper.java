/* 
 * This file is part of the GROUCHO project.
 * 
 * GROUCHO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GROUCHO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GROUCHO.  If not, see <https://www.gnu.org/licenses/>
 *
 */
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.apache.jcs.engine.behavior.ICacheElement;
import org.apache.jcs.engine.behavior.ICompositeCacheAttributes;
import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.memory.MemoryCache;
import org.apache.jcs.engine.stats.behavior.IStats;

public class MemoryCacheWrapper implements MemoryCache{

	private MemoryCache memory;
	
	private int waterfallCallCounter;
	
	public MemoryCacheWrapper (MemoryCache m) {
		this.memory = m;
		this.waterfallCallCounter = 0;
	}
	
	@Override
	public void initialize(CompositeCache cache) {
		this.memory.initialize(cache);
	}

	@Override
	public void dispose() throws IOException {
		this.waterfallCallCounter = 0;
		this.memory.dispose();
	}

	@Override
	public int getSize() {
		return this.memory.getSize();
	}

	@Override
	public IStats getStatistics() {
		return this.memory.getStatistics();
	}

	@Override
	public Iterator getIterator() {
		return this.memory.getIterator();
	}

	@Override
	public Object[] getKeyArray() {
		return this.memory.getKeyArray();
	}

	@Override
	public boolean remove(Serializable key) throws IOException {
		return this.memory.remove(key);
	}

	@Override
	public void removeAll() throws IOException {
		this.memory.removeAll();
	}

	@Override
	public int freeElements(int numberToFree) throws IOException {
		return this.memory.freeElements(numberToFree);
	}

	@Override
	public ICacheElement get(Serializable key) throws IOException {
		return this.memory.get(key);
	}

	@Override
	public ICacheElement getQuiet(Serializable key) throws IOException {
		return this.memory.getQuiet(key);
	}

	@Override
	public void waterfal(ICacheElement ce) throws IOException {
		this.waterfallCallCounter ++;
		this.memory.waterfal(ce);
	}

	@Override
	public void update(ICacheElement ce) throws IOException {
		this.memory.update(ce);
	}

	@Override
	public ICompositeCacheAttributes getCacheAttributes() {
		return this.memory.getCacheAttributes();
	}

	@Override
	public void setCacheAttributes(ICompositeCacheAttributes cattr) {
		this.memory.setCacheAttributes(cattr);
	}

	@Override
	public CompositeCache getCompositeCache() {
		return this.memory.getCompositeCache();
	}

	@Override
	public Set getGroupKeys(String group) {
		return this.memory.getGroupKeys(group);
	}

	public int getWaterfallCallCounter() {
		return this.waterfallCallCounter;
	}

}
