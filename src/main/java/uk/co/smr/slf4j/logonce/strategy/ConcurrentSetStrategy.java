package uk.co.smr.slf4j.logonce.strategy;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import uk.co.smr.slf4j.logonce.MatchingStrategy;

public class ConcurrentSetStrategy implements MatchingStrategy {
	private final Set<String> filter = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

	@Override
	public boolean add(String msg) {
		return filter.add(msg);
	}

}
