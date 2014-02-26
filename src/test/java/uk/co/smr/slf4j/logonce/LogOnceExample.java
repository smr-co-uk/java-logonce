package uk.co.smr.slf4j.logonce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.smr.slf4j.logonce.MatchingStrategyFactory.Reliability;
import uk.co.smr.slf4j.logonce.MatchingStrategyFactory.Safety;

public class LogOnceExample {
	
	@SuppressWarnings("unused")
	void defaultlogger() {
		Logger logger = LoggerFactory.getLogger(LogOnceExample.class); 
		LogOnce logonce = new LogOnce(logger);
	}

	@SuppressWarnings("unused")
	void safereliablelogger() {
		Logger logger = LoggerFactory.getLogger(LogOnceExample.class); 
		LogOnce logonce = new LogOnce(logger, MatchingStrategyFactory.create(Safety.safe, Reliability.all));
	}
}
