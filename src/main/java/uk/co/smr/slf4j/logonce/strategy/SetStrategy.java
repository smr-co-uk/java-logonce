package uk.co.smr.slf4j.logonce.strategy;

import java.util.HashSet;
import java.util.Set;

import uk.co.smr.slf4j.logonce.MatchingStrategy;

/**
 * A strategy to filter all logged messages backed by a set. 
 * There are no false positives.
 * 
 * <p>This class is <b>not</b> thread safe.
 * 
 * @author Peter Lappo (smr.co.uk)
 * 
 * @see <a href="https://github.com/smr-co-uk/java-logonce/blob/master/LICENSE">License and Warranty</a>
 *
 */

public class SetStrategy implements MatchingStrategy {
	private final Set<String> filter = new HashSet<String>();

	@Override
	public boolean add(String msg) {
		return filter.add(msg);
	}

}
