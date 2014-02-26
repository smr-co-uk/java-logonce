package uk.co.smr.slf4j.logonce;

/**
 * Interface for matching strategy plugins. 
 * The strategy is responsible for deciding if the message has been seen before.
 * 
 * @author Peter Lappo (smr.co.uk)
 * 
 * @see <a href="https://github.com/smr-co-uk/java-logonce/blob/master/LICENSE">License and Warranty</a>
 *
 */

public interface MatchingStrategy {

	/**
	 * Add the message and return true if the message has not been seen before and needs to be logged.
	 * 
	 * @param message
	 * @return true if the message needs to be logged
	 */
	boolean add(String message);

}
