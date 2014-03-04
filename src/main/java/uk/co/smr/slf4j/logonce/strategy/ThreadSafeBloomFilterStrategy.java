package uk.co.smr.slf4j.logonce.strategy;

import java.nio.charset.Charset;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * A space efficient BloomFilter is used to filter logged messages. 
 * It will occasionally result in false positives. That is, sometimes
 * messages will be logged more than once.
 * 
 * <p>This class is thread safe.
 * 
 * @author Peter Lappo (smr.co.uk)
 * 
 * @see <a href="https://github.com/smr-co-uk/java-logonce/blob/master/LICENSE">License and Warranty</a>
 *
 */

public class ThreadSafeBloomFilterStrategy extends BloomFilterStrategy {
	private final ReentrantLock lock = new ReentrantLock();
	private final BloomFilter<CharSequence> filter;
	
	public ThreadSafeBloomFilterStrategy() {
		this(2000, 0.03);
	}

	/**
	 * @see BloomFilter
	 * 
	 * @param expectedInsertions the number of expected insertions to the constructed
	 * @param fpp false positive probability must be positive, decimal fraction, eg. 0.03 for 3%
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
