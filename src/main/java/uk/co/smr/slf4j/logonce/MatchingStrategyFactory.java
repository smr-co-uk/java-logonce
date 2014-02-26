package uk.co.smr.slf4j.logonce;

import uk.co.smr.slf4j.logonce.strategy.BloomFilterStrategy;
import uk.co.smr.slf4j.logonce.strategy.SetStrategy;
import uk.co.smr.slf4j.logonce.strategy.ThreadSafeBloomFilterStrategy;
import uk.co.smr.slf4j.logonce.strategy.ThreadSafeSetStrategy;

/**
 * Factory for creating instances of log message matching strategies.
 * Two dimensions can be specified, thread safe or unsafe and how reliable the matching process is, that is most or all.
 * 
 * @author Peter Lappo (smr.co.uk)
 * 
 * @see <a href="https://github.com/smr-co-uk/java-logonce/blob/master/LICENSE">License and Warranty</a>
 *
 */

final public class MatchingStrategyFactory {

	public enum Safety { safe, unsafe };
	public enum Reliability { most, all };
	
	public static MatchingStrategy create(Safety threadSafety, Reliability reliability) {
		switch (threadSafety) {
			case unsafe:
				switch (reliability) {
					case all:
						return new SetStrategy();
					case most:
						return new BloomFilterStrategy();
					default:
				}
				break;
			case safe:
				switch (reliability) {
					case all:
						return new ThreadSafeSetStrategy();
					case most:
						return new ThreadSafeBloomFilterStrategy();
					default:
				}
				break;
			default: 
		}
		throw new UnsupportedOperationException("Unknown Strategy");
	}
	
	private MatchingStrategyFactory() { } // prevent instantiation
}
