package uk.co.smr.slf4j.logonce;

/**
 * 
 * @author peter
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
