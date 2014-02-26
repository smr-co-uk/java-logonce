java-logonce
============
## User Story
As a developer I want to log state so that I can debug a production issue without adding a lot of duplicates to the log file 
and I want to use my existing logging framework.

## Description
This is an slf4j logger that only logs on the first instance of message it sees 
thereafter that instance is ignored until the logger is garbage collected. 
LogOnce is backed by two filtering strategies with thread safe (ie slower) and non thread safe variants (ie faster).

The two strategies are:-
* a space efficient Bloomer filter from Guava which may result in some false positives so some messages
may be logged more than once. The expected false positive probability is 3%.
* a set backed filter which stores all seen messages so doesn't log more than once.

The thread safer version of the Bloomfilter strategy uses a Reentrant lock to ensures it's consistently updated in a multi-threaded environment.
While the Set backed strategy uses a set version of ConcurrentHashMap.

Continuous Integration results can be seen here https://travis-ci.org/smr-co-uk/java-logonce

Readable JBehave BBD acceptance tests are used to test the code.

Its used in conjunction with an existing logger as follows:-

```java
import org.slf4j.LoggerFactory;
...
Logger logger = LoggerFactory.getLogger(...);
LogOnce logonce = new LogOnce(logger);
```

There are three constructors as follows:- 
* new LogOnce(logger);
 * Thread safe bloomfilter with ONCE: prefixed to the message
* new LogOnce(logger, MatchingStrategy);
 * As above but control the MatchingStrategy. 
 * Use MatchingStrategyFactory.create a strategy with a specification for Safety and Reliability
 * MatchingStrategyFactory.create(Safety.safe, Reliability.all);
* new LogOnce(logger, prefix, MatchingStrategy);
 * As above but include / exclude the prefix

## To Install
Clone from github https://github.com/smr-co-uk/java-logonce.git or download a zip file 
https://github.com/smr-co-uk/java-logonce/archive/master.zip
The project is configured to work with Eclipse http://www.eclipse.org/

## To Test
After installing, on U/Linux in a terminal window type
```
gradlew check
```

On Windows in a command window type
```
gradlew.bat check
```

## Road Map
* Add maven central repository support.

