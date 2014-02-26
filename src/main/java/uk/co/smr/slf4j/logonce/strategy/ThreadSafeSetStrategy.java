package uk.co.smr.slf4j.logonce.strategy;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import uk.co.smr.slf4j.logonce.MatchingStrategy;

/**
 * A strategy to filter all logged messages backed by a set. 
 * There are no false positives.
 * 
 * <p>This class is thread safe.
 * 
 * @author Peter Lappo (smr.co.uk)
 * 
 * @see <a href="https://github.com/smr-co-uk/java-logonce/blob/master/LICENSE">License and Warranty</a>
 *
 */

public class ThreadSafeSetStrategy implements MatchingStrategy {
	private final Set<String> filter = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

	@Override
	public boolean add(String msg) {
		return filter.add(msg);
	}

}
