package uk.co.smr.slf4j.logonce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger.Level;

import uk.co.smr.slf4j.logonce.MatchingStrategyFactory.Reliability;
import uk.co.smr.slf4j.logonce.MatchingStrategyFactory.Safety;
import uk.co.smr.slf4j.logonce.strategy.BloomFilterStrategy;

public class LogOnceSteps {

	private LogOnce logger;
	
	@Given("a logger $logger")
	public void logOnce(@Named("logger") String loggerName) {
    	logger = new LogOnce(LoggerFactory.getLogger(loggerName));
	}
 
	@Given("a logger without ONCE: $logger")
	public void logOnceWithoutOnce(@Named("logger") String loggerName) {
    	logger = new LogOnce(LoggerFactory.getLogger(loggerName), false, new BloomFilterStrategy());
	}
 
	@Given("a matching strategy with $safety and $reliability and logger $logger")
	public void logOnceWithStrategy(@Named("safety") Safety safety, @Named("reliability") Reliability reliability, @Named("logger") String loggerName) {
		MatchingStrategy thestrategy =  MatchingStrategyFactory.create(safety, reliability);
    	logger = new LogOnce(LoggerFactory.getLogger(loggerName), thestrategy);
	}
 
	@When("I log one message $message for $times times at level $level")
	public void whenILogAMessageAtLevel(@Named("message") String message
			, @Named("times") int times
			, @Named("level") Level level) {
		switch (level) {
		case TRACE:
			for (int i=0; i<times; i++) {
				logger.trace(message);
			}
			break;
		case DEBUG:
			for (int i=0; i<times; i++) {
				logger.debug(message);
			}
			break;
		case INFO:
			for (int i=0; i<times; i++) {
				logger.info(message);
			}
			break;
		case WARN:
			for (int i=0; i<times; i++) {
				logger.warn(message);
			}
			break;
		case ERROR:
			for (int i=0; i<times; i++) {
				logger.error(message);
			}
			break;
		default:
			break;
		}
	}
	
	@When("I log $several different messages made from $message for $times times at level $level")
	public void whenILogSeveralMessageAtLevel(@Named("message") String message
			, @Named("times") int times
			, @Named("level") Level level
			, @Named("several") int several) {
		for (int i=0; i < times; i++) {
			for (int j=0; j < several; j++) {
				whenILogAMessageAtLevel(message + j, 1, level);
			}
		}
	}
	
	@Then("ignored should equal $ignored and logged should equal $logged")
	public void ingnoredShouldEqual(@Named("ignored") int ignored, @Named("logged") int logged) {
		assertEquals("ignored error", ignored, logger.getIgnored());
		assertEquals("logged error", logged, logger.getLogged());
	}
	
	@Then("logger should contain Hello World at $level if logged $logged is greater than zero")
	public void loggerShouldContain(@Named("level") String level, @Named("logged") int logged) throws Exception {
		if (logged == 0) {
			return;
		}
		File log = new File("logonce.log");
		assertTrue("logonce.log should exist", log.exists() && log.canRead());
		String lastline = getLastLine(log, null);
		assertNotNull(lastline);
		assertTrue("Should contain Hello World", lastline.contains("Hello World"));
		assertTrue("Should contain ONCE:", lastline.contains("ONCE:"));
		assertTrue("Should contain " + level, lastline.contains(level));
	}

	@Then("logger should contain a stack trace and Hello World at $level if logged $logged is greater than zero")
	public void loggerShouldContainStackTrace(@Named("level") String level, @Named("logged") int logged) throws Exception {
		if (logged == 0) {
			return;
		}
		File log = new File("logonce.log");
		assertTrue("logonce.log should exist", log.exists() && log.canRead());
		String lastline = getLastLine(log, level);
		assertNotNull(lastline);
		assertTrue("Should contain Hello World", lastline.contains("Hello World"));
		assertTrue("Should contain ONCE:", lastline.contains("ONCE:"));
		assertTrue("Should contain " + level, lastline.contains(level));
		lastline = getLastLine(log, null);
		assertTrue("Should contain 'at ' from the stack trace", lastline.contains("at "));
	}

	private String getLastLine(File log, String filter) throws FileNotFoundException, IOException {
		String lastline = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(log));
			String line = null;
			// not very efficient, but its only a small file
			if (filter == null) {
				while ((line = reader.readLine()) != null) {
					lastline = line;
				}
			} else {
				while ((line = reader.readLine()) != null) {
					if (line.contains(filter)) {
						lastline = line;
					}
				}
				
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return lastline;
	}
	
	@When(value="I log a formatted message $message with one parameter $parameter for $times times at level $level")
	// make when unambiguous to avoid brittle use of , priority = 1)
	public void whenILogMessageWithOneParameter(@Named("message") String message,
			@Named("parameter") String parameter,
			@Named("times") int times,
			@Named("level") Level level
			) {
		switch (level) {
		case TRACE:
			for (int i=0; i<times; i++) {
				logger.trace(message, parameter);
			}
			break;
		case DEBUG:
			for (int i=0; i<times; i++) {
				logger.debug(message, parameter);
			}
			break;
		case INFO:
			for (int i=0; i<times; i++) {
				logger.info(message, parameter);
			}
			break;
		case WARN:
			for (int i=0; i<times; i++) {
				logger.warn(message, parameter);
			}
			break;
		case ERROR:
			for (int i=0; i<times; i++) {
				logger.error(message, parameter);
			}
			break;
		default:
			break;
		}
	}
	
	@When(value="I log a formatted message $message with two parameters $parameterOne and $parameterTwo for $times times at level $level")
	// make when unambiguous to avoid brittle use of , priority = 1)
	public void whenILogMessageWithTwoParameters(@Named("message") String message,
			@Named("parameterOne") String parameterOne,
			@Named("parameterTwo") String parameterTwo,
			@Named("times") int times,
			@Named("level") Level level
			) {
		switch (level) {
		case TRACE:
			for (int i=0; i<times; i++) {
				logger.trace(message, parameterOne, parameterTwo);
			}
			break;
		case DEBUG:
			for (int i=0; i<times; i++) {
				logger.debug(message, parameterOne, parameterTwo);
			}
			break;
		case INFO:
			for (int i=0; i<times; i++) {
				logger.info(message, parameterOne, parameterTwo);
			}
			break;
		case WARN:
			for (int i=0; i<times; i++) {
				logger.warn(message, parameterOne, parameterTwo);
			}
			break;
		case ERROR:
			for (int i=0; i<times; i++) {
				logger.error(message, parameterOne, parameterTwo);
			}
			break;
		default:
			break;
		}
	}
	
	@When(value="I log a formatted message with three parameters for $message and $parameterOne and $parameterTwo and $parameterThree for $times times at level $level")
	// make when unambiguous to avoid brittle use of , priority = 1)
	public void whenILogMessageWithThreeParameters(@Named("message") String message,
			@Named("parameterOne") String parameterOne,
			@Named("parameterTwo") String parameterTwo,
			@Named("parameterThree") String parameterThree,
			@Named("times") int times,
			@Named("level") Level level
			) {
		switch (level) {
		case TRACE:
			for (int i=0; i<times; i++) {
				logger.trace(message, new Object[] {parameterOne, parameterTwo, parameterThree});
			}
			break;
		case DEBUG:
			for (int i=0; i<times; i++) {
				logger.debug(message, new Object[] {parameterOne, parameterTwo, parameterThree});
			}
			break;
		case INFO:
			for (int i=0; i<times; i++) {
				logger.info(message, new Object[] {parameterOne, parameterTwo, parameterThree});
			}
			break;
		case WARN:
			for (int i=0; i<times; i++) {
				logger.warn(message, new Object[] {parameterOne, parameterTwo, parameterThree});
			}
			break;
		case ERROR:
			for (int i=0; i<times; i++) {
				logger.error(message, new Object[] {parameterOne, parameterTwo, parameterThree});
			}
			break;
		default:
			break;
		}
	}
	
	@When(value="I log a formatted message with a throwable for $message for $times times at level $level")
	public void whenILogMessageWithAThrowable(@Named("message") String message,
			@Named("times") int times,
			@Named("level") Level level
			) {
		switch (level) {
		case TRACE:
			for (int i=0; i<times; i++) {
				logger.trace(message, new Throwable());
			}
			break;
		case DEBUG:
			for (int i=0; i<times; i++) {
				logger.debug(message, new Throwable());
			}
			break;
		case INFO:
			for (int i=0; i<times; i++) {
				logger.info(message, new Throwable());
			}
			break;
		case WARN:
			for (int i=0; i<times; i++) {
				logger.warn(message, new Throwable());
			}
			break;
		case ERROR:
			for (int i=0; i<times; i++) {
				logger.error(message, new Throwable());
			}
			break;
		default:
			break;
		}
	}
	
	@AfterStories
	public void cleanup() {
	}
 
// Redundant, just playing around
//	@Then("another ignored should equal $ignored and logged should equal $logged")
//	public void anotherIgnoredShouldEqual(@Named("ignored") int ignored, @Named("logged") int logged) {
//		assertEquals(logger.getIgnored(), ignored);
//		assertEquals(logger.getLogged(), logged);
//	}
	

}
