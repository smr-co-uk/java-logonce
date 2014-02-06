package uk.co.smr.slf4j.logonce;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.Level;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.LoggerFactory;

public class LogOnceSteps {

	protected LogOnce logger;
	
	@Given("a logger $logger")
	public void logOnce(@Named("logger") String loggerName) {
    	logger = new LogOnce(LoggerFactory.getLogger(loggerName));
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
 
	@Then("ignored should equal $ignored and logged should equal $logged")
	public void ingnoredShouldEqual(@Named("ignored") int ignored, @Named("logged") int logged) {
		assertEquals(logger.getIgnored(), ignored);
		assertEquals(logger.getLogged(), logged);
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
 
// Redundant, just playing around
//	@Then("another ignored should equal $ignored and logged should equal $logged")
//	public void anotherIgnoredShouldEqual(@Named("ignored") int ignored, @Named("logged") int logged) {
//		assertEquals(logger.getIgnored(), ignored);
//		assertEquals(logger.getLogged(), logged);
//	}
	

}
