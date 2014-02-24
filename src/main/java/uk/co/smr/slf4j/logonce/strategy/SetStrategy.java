package uk.co.smr.slf4j.logonce.strategy;

import java.util.HashSet;
import java.util.Set;

import uk.co.smr.slf4j.logonce.MatchingStrategy;

public class SetStrategy implements MatchingStrategy {
	private final Set<String> filter = new HashSet<String>();

	@Override
	public boolean add(String msg) {
		return filter.add(msg);
	}

}
