package it.cnr.iasi.saks.foo;

import com.opensymphony.oscache.base.algorithm.LRUCache;

public class MyDummyLRUCache extends LRUCache {
	
    public MyDummyLRUCache() {
        super();
        
    }

    public MyDummyLRUCache(int capacity) {
        super(capacity);
    }

	
    @Override
	public boolean containsValue(Object value) {
        boolean result = super.containsValue(value);
    	return result;
    }

    @Override
	public int size() {
        int result = super.size();
    	return result;
    }


}
