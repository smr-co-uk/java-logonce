Narrative:
In order to logonce
As a client
I want to log messages once using a different strategy

Scenario: Simple Several Different Messages and Set Strategy

Given a matching strategy <strategy> and logger <logger> 
When I log <several> different messages made from <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|times|level|ignored|logged|several|strategy|
|Any|Hello World|3|TRACE|10|5|5|Set|
|--
|HelloWorld_Trace|Hello World|3|TRACE|10000|5000|5000|Set|
|HelloWorld_Trace|Hello World|3|DEBUG|1000|500|500|Set|
|HelloWorld_Trace|Hello World|3|INFO|1000|500|500|Set|
|HelloWorld_Trace|Hello World|3|WARN|1000|500|500|Set|
|HelloWorld_Trace|Hello World|3|ERROR|1000|500|500|Set|
|--
|HelloWorld_Debug|Hello World|3|TRACE|0|0|5|Set|  
|HelloWorld_Debug|Hello World|3|DEBUG|1000|500|500|Set|
|HelloWorld_Debug|Hello World|3|INFO|1000|500|500|Set|
|HelloWorld_Debug|Hello World|3|WARN|1000|500|500|Set|
|HelloWorld_Debug|Hello World|3|ERROR|1000|500|500|Set|
|--
|HelloWorld_Info|Hello World|3|TRACE|0|0|5|Set|
|HelloWorld_Info|Hello World|3|DEBUG|0|0|5|Set|
|HelloWorld_Info|Hello World|3|INFO|1000|500|500|Set|
|HelloWorld_Info|Hello World|3|WARN|1000|500|500|Set|
|HelloWorld_Info|Hello World|3|ERROR|1000|500|500|Set|
|--
|HelloWorld_Warn|Hello World|3|TRACE|0|0|5|Set| 
|HelloWorld_Warn|Hello World|3|DEBUG|0|0|5|Set|
|HelloWorld_Warn|Hello World|3|INFO|0|0|5|Set|
|HelloWorld_Warn|Hello World|3|WARN|1000|500|500|Set|
|HelloWorld_Warn|Hello World|3|ERROR|1000|500|500|Set|
|--
|HelloWorld_Error|Hello World|3|TRACE|0|0|5|Set|
|HelloWorld_Error|Hello World|3|DEBUG|0|0|5|Set|
|HelloWorld_Error|Hello World|3|INFO|0|0|5|Set|
|HelloWorld_Error|Hello World|3|WARN|0|0|5|Set| 
|HelloWorld_Error|Hello World|3|ERROR|1000|500|500|Set|
