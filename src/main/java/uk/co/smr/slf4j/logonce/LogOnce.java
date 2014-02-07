package uk.co.smr.slf4j.logonce;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * LogOnce checks if the message has already been logged and if not logs the message.
 * Its useful in a context when only one message per process instance should be logged to guard 
 * against too many messages hitting a log file or if an error condition needs to be logged but
 * only once. It uses slf4j for greater portability and more efficient logging. 
 * A space efficient BloomFilter is used but it will occasionally result in false positives. 
 * The class follows the slf4j pattern by checking the is<level>Enabled first.
 * The class is thread safe.
 * <p>
 * Its used in conjunction with an existing logger as follows:-
 * <p>
 * <code>
 * Logger logger = LoggerFactory.getLogger("HelloWorld");
 * <br>
 * LogOnce logonce = new LogOnce(logger);
 * </code>
 * 
 * @author Peter Lappo (smr.co.uk)
 *
 */

public class LogOnce implements Logger {

    private static final String ONCE = "ONCE: {}";
	private final BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000);
    private final Logger delegate;
    private final boolean prefix;
	private final AtomicLong ignored = new AtomicLong();
	private final AtomicLong logged = new AtomicLong();
	private final ReentrantLock lock = new ReentrantLock();

    public LogOnce(Logger logger) {
    	this(logger, true);
	}
    
    public LogOnce(Logger logger, boolean prefix) {
		Preconditions.checkNotNull(logger, "Logger delegate must not be null");
		this.delegate = logger;
    	this.prefix = prefix;
    }
    
	@Override
	public String getName() {
		return delegate.getName();
	}
	
	/**
	 * @return the count of ignored messages
	 */
	public long getIgnored() {
		return ignored.get();
	}

	/**
	 * @return the count of logged messages
	 */
	public long getLogged() {
		return logged.get();
	}

	@Override
	public boolean isTraceEnabled() {
		return delegate.isTraceEnabled();
	}

	@Override
	public void trace(String msg) {
		trace((Marker)null, msg);
	}

	@Override
	public void trace(String format, Object arg) {
		trace((Marker)null, format, arg);
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		trace((Marker)null, format, arg1, arg2);
	}

	@Override
	public void trace(String format, Object... args) {
		trace((Marker)null, format, args);
	}

	@Override
	public void trace(String msg, Throwable t) {
		trace((Marker) null, msg, t);
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return delegate.isTraceEnabled(marker);
	}

	@Override
	public void trace(Marker marker, String msg) {
	    if (!delegate.isTraceEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
	    	if (prefix) {
	    		delegate.trace(marker, ONCE, msg);
	    	} else {
	    		delegate.trace(marker, msg);
	    	}
	    }
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
	    if (!delegate.isTraceEnabled(marker)){
	        return;
	    }
	    trace(marker, MessageFormatter.arrayFormat(format, new Object[] { arg }).getMessage());
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
	    if (!delegate.isTraceEnabled(marker)){
	        return;
	    }
	    trace(marker, MessageFormatter.arrayFormat(format, new Object[] { arg1, arg2 }).getMessage());
	}

	@Override
	public void trace(Marker marker, String format, Object... args) {
	    if (!delegate.isTraceEnabled(marker)){
	        return;
	    }
	    trace(marker, MessageFormatter.arrayFormat(format, args).getMessage());
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
	    if (!delegate.isTraceEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
		    if (registerMessageSync(msg)) { 
		    	if (prefix) {
		    		delegate.trace(marker, MessageFormatter.arrayFormat(ONCE, new Object[] { msg }).getMessage(), t);
		    	} else {
		    		delegate.trace(marker, msg, t);
		    	}
		    }
	    }
	}

	@Override
	public boolean isDebugEnabled() {
		return delegate.isDebugEnabled();
	}

	@Override
	public void debug(String msg) {
		debug((Marker)null, msg);
	}

	@Override
	public void debug(String format, Object arg) {
	    debug((Marker)null, format, arg);
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
	    debug((Marker)null, format, arg1, arg2);
	}

	@Override
	public void debug(String format, Object... args) {
	    debug((Marker)null, format, args);
	}

	@Override
	public void debug(String msg, Throwable t) {
		debug((Marker) null, msg, t);
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return delegate.isDebugEnabled(marker);
	}

	@Override
	public void debug(Marker marker, String msg) {
	    if (!delegate.isDebugEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
	    	if (prefix) {
	    		delegate.debug(marker, ONCE, msg);
	    	} else {
	    		delegate.debug(marker, msg);
	    	}
	    }
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
	    if (!delegate.isDebugEnabled(marker)){
	        return;
	    }
	    debug(marker, MessageFormatter.arrayFormat(format, new Object[] { arg }).getMessage());
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
	    if (!delegate.isDebugEnabled(marker)){
	        return;
	    }
	    debug(marker, MessageFormatter.arrayFormat(format, new Object[] { arg1, arg2 }).getMessage());
	}

	@Override
	public void debug(Marker marker, String format, Object... args) {
	    if (!delegate.isDebugEnabled(marker)){
	        return;
	    }
	    debug(marker, MessageFormatter.arrayFormat(format, args).getMessage());
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
	    if (!delegate.isDebugEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
		    if (registerMessageSync(msg)) { 
		    	if (prefix) {
		    		delegate.debug(marker, MessageFormatter.arrayFormat(ONCE, new Object[] { msg }).getMessage(), t);
		    	} else {
		    		delegate.debug(marker, msg, t);
		    	}
		    }
	    }
	}

	@Override
	public boolean isInfoEnabled() {
		return delegate.isInfoEnabled();
	}

	@Override
	public void info(String msg) {
		info((Marker)null, msg);
	}

	@Override
	public void info(String format, Object arg) {
		info((Marker)null, format, arg);
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		info((Marker)null, format, arg1, arg2);
	}

	@Override
	public void info(String format, Object... args) {
		info((Marker)null, format, args);
	}

	@Override
	public void info(String msg, Throwable t) {
		info((Marker) null, msg, t);
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return delegate.isInfoEnabled(marker);
	}

	@Override
	public void info(Marker marker, String msg) {
	    if (!delegate.isInfoEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
	    	if (prefix) {
	    		delegate.info(marker, ONCE, msg);
	    	} else {
	    		delegate.info(marker, msg);
	    	}
	    }
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
	    if (!delegate.isInfoEnabled(marker)){
	        return;
	    }
	    info(marker, MessageFormatter.arrayFormat(format, new Object[] { arg }).getMessage());
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
	    if (!delegate.isInfoEnabled(marker)){
	        return;
	    }
	    info(marker, MessageFormatter.arrayFormat(format, new Object[] { arg1, arg2 }).getMessage());
	}

	@Override
	public void info(Marker marker, String format, Object... args) {
	    if (!delegate.isInfoEnabled(marker)){
	        return;
	    }
	    info(marker, MessageFormatter.arrayFormat(format, args).getMessage());
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
	    if (!delegate.isInfoEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
		    if (registerMessageSync(msg)) { 
		    	if (prefix) {
		    		delegate.info(marker, MessageFormatter.arrayFormat(ONCE, new Object[] { msg }).getMessage(), t);
		    	} else {
		    		delegate.info(marker, msg, t);
		    	}
		    }
	    }
	}

	@Override
	public boolean isWarnEnabled() {
		return delegate.isWarnEnabled();
	}

	@Override
	public void warn(String msg) {
		warn((Marker)null, msg);
	}

	@Override
	public void warn(String format, Object arg) {
		warn((Marker) null, format, arg);
	}

	@Override
	public void warn(String format, Object... args) {
		warn((Marker) null, format, args);
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		warn((Marker) null, format, arg1, arg2);
	}

	@Override
	public void warn(String msg, Throwable t) {
		warn((Marker) null, msg, t);
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return delegate.isWarnEnabled(marker);
	}

	@Override
	public void warn(Marker marker, String msg) {
	    if (!delegate.isWarnEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
	    	if (prefix) {
	    		delegate.warn(marker, ONCE, msg);
	    	} else {
	    		delegate.warn(marker, msg);
	    	}
	    }
	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
	    if (!delegate.isWarnEnabled(marker)){
	        return;
	    }
	    warn(marker, MessageFormatter.arrayFormat(format, new Object[] { arg }).getMessage());
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
	    if (!delegate.isWarnEnabled(marker)){
	        return;
	    }
	    warn(marker, MessageFormatter.arrayFormat(format, new Object[] { arg1, arg2 }).getMessage());
	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
	    if (!delegate.isWarnEnabled(marker)){
	        return;
	    }
	    warn(marker, MessageFormatter.arrayFormat(format, arguments).getMessage());
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
	    if (!delegate.isWarnEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
		    if (registerMessageSync(msg)) { 
		    	if (prefix) {
		    		delegate.warn(marker, MessageFormatter.arrayFormat(ONCE, new Object[] { msg }).getMessage(), t);
		    	} else {
		    		delegate.warn(marker, msg, t);
		    	}
		    }
	    }
	}

	@Override
	public boolean isErrorEnabled() {
		return delegate.isErrorEnabled();
	}

	@Override
	public void error(String msg) {
		error((Marker) null, msg);
	}

	@Override
	public void error(String format, Object arg) {
		error((Marker) null, format, arg);
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		error((Marker) null, format, arg1, arg2);
	}

	@Override
	public void error(String format, Object... args) {
		error((Marker) null, format, args);
	}

	@Override
	public void error(String msg, Throwable t) {
		error((Marker) null, msg, t);
	}


	@Override
	public boolean isErrorEnabled(Marker marker) {
		return delegate.isErrorEnabled(marker);
	}

	@Override
	public void error(Marker marker, String msg) {
	    if (!delegate.isErrorEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
	    	if (prefix) {
	    		delegate.error(marker, ONCE, msg);
	    	} else {
	    		delegate.error(marker, msg);
	    	}
	    }
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
	    if (!delegate.isErrorEnabled(marker)){
	        return;
	    }
	    error(marker, MessageFormatter.arrayFormat(format, new Object[] { arg }).getMessage());
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
	    if (!delegate.isErrorEnabled(marker)){
	        return;
	    }
	    error(marker, MessageFormatter.arrayFormat(format, new Object[] { arg1, arg2 }).getMessage());
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
	    if (!delegate.isErrorEnabled(marker)){
	        return;
	    }
	    error(marker, MessageFormatter.arrayFormat(format, arguments).getMessage());
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
	    if (!delegate.isErrorEnabled(marker)){
	        return;
	    }
	    if (registerMessageSync(msg)) { 
	    	if (prefix) {
	    		delegate.error(marker, MessageFormatter.arrayFormat(ONCE, new Object[] { msg }).getMessage(), t);
	    	} else {
	    		delegate.error(marker, msg, t);
	    	}
	    }
	}

	protected boolean registerMessageSync(String msg) {
		boolean logmsg = false;
		lock.lock();
		try {
		    if (!filter.mightContain(msg)) {
		    	filter.put(msg);
		    	logmsg = true;
		    } 
		} finally {
			lock.unlock();
		}
		if (logmsg) {
			logged.incrementAndGet();
		}
		else {
			ignored.incrementAndGet();
		}
		return logmsg;
	}

}
