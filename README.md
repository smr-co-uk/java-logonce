java-logonce
============

A thread safe slf4j logger that only logs on the first instance of message it sees 
thereafter that instance is ignored until the logger is garbage collected. 
LogOnce is backed by a space efficient Bloomer filter from Guava which may result in some false positives so some messages
may be logged more than once. 
The Bloomfilter is not thread safe so a Reentrant lock ensures it's consistently updated in a multi-threaded environment.

Continuous Integration results can be seen here https://travis-ci.org/smr-co-uk/java-logonce

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