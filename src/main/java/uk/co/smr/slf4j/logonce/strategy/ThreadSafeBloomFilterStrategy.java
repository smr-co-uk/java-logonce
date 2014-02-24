package uk.co.smr.slf4j.logonce.strategy;

import java.nio.charset.Charset;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class ThreadSafeBloomFilterStrategy extends BloomFilterStrategy {
	private final ReentrantLock lock = new ReentrantLock();
	private final BloomFilter<CharSequence> filter;
	
	public ThreadSafeBloomFilterStrategy() {
		filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000);
	}

	/**
	 * @see BloomFilter
	 * @param funnel the funnel of T's that the constructed {@code BloomFilter<T>} will use
	 * @param expectedInsertions the number of expected insertions to the constructed
	 *     {@code BloomFilter<T>}; must be positive, decimal fraction, eg. 0.03 for 3%
	 */
	public ThreadSafeBloomFilterStrategy(int expectedInsertions, double fpp) {
		filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, fpp);
	}

	@Override
	public boolean add(String msg) {
		boolean logmsg = false;
		lock.lock();
		try {
		    if (!filter.mightContain(msg)) {
		    	filter.put(msg);
		    	logmsg = true;
		    } 
		} finally {
			lock.unlock();
		}
		return logmsg;
	}

}
