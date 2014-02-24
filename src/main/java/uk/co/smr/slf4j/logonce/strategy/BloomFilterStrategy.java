package uk.co.smr.slf4j.logonce.strategy;

import java.nio.charset.Charset;

import uk.co.smr.slf4j.logonce.MatchingStrategy;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterStrategy implements MatchingStrategy {
	private final BloomFilter<CharSequence> filter;
	
	public BloomFilterStrategy() {
		filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 2000, 0.03);
	}

	/**
	 * @see BloomFilter
	 * @param funnel the funnel of T's that the constructed {@code BloomFilter<T>} will use
	 * @param expectedInsertions the number of expected insertions to the constructed
	 *     {@code BloomFilter<T>}; must be positive, decimal fraction, eg. 0.03 for 3%
	 */
	public BloomFilterStrategy(int expectedInsertions, double fpp) {
		filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, fpp);
	}

	@Override
	public boolean add(String msg) {
		boolean logmsg = false;
	    if (!filter.mightContain(msg)) {
	    	filter.put(msg);
	    	logmsg = true;
	    } 
		return logmsg;
	}

}
