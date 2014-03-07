java-logonce
============
Logonce is useful for investigating production issues and reducing log overhead when 
there is a risk of logging a large number of repeated message.

## User Story
As a developer I want to log detailed log messages without the risk of spamming the log 
in addition I want to reuse my existing Java logging framework.

## To Install
Maven central http://repo1.maven.org/maven2/uk/co/smr/logonce/
* groupId = 'uk.co.smr' 
* artifactId = logonce
* version = 0.1.2
* gradle = 'uk.co.smr:logonce:0.1.2'

Or clone from github https://github.com/smr-co-uk/java-logonce.git 

The project is configured to work with Eclipse http://www.eclipse.org/

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
* No further features are planned.

