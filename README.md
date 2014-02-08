java-logonce
============
## User Story
As a developer I want to log state so that I can debug a production issue without adding a lot of duplicates to the log file.

## Description
A thread safe slf4j logger that only logs on the first instance of message it sees 
thereafter that instance is ignored until the logger is garbage collected. 
LogOnce is backed by a space efficient Bloomer filter from Guava which may result in some false positives so some messages
may be not be logged. The expected false positive probability is 3%.
The Bloomfilter is not thread safe so a Reentrant lock ensures it's consistently updated in a multi-threaded environment.

Continuous Integration results can be seen here https://travis-ci.org/smr-co-uk/java-logonce

Readable JBehave BBD acceptance tests are used to test the code.

Its used in conjunction with an existing logger as follows:-

```java
import org.slf4j.LoggerFactory;
...
Logger logger = LoggerFactory.getLogger(...);
LogOnce logonce = new LogOnce(logger);
```
There are two constructors for LogOnce, one which does not alter the message and one which prefixes ONCE 
to the start of the message to indicate LogOnce is being used. The default is to add ONCE.

```java
LogOnce logonce = new LogOnce(logger, false); // to turn off ONCE: prefix
```

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
* Bloomfilter suffer from false positives so the next type of LogOnce logger will ensure there are no false positives.
* Add a factory method to specify what your requirements are in terms of false positives, concurrency etc...

