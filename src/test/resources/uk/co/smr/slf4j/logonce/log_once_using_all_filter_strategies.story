Narrative:
In order to logonce
As a client
I want to log messages once using a different strategy

Scenario: Simple Several Different Messages and unsafe|all [Set] Strategy

Given a matching strategy with <safety> and <reliability> and logger <logger> 
When I log <several> different messages made from <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|times|level|ignored|logged|several|safety|reliability|
|Any|Hello World|3|TRACE|10|5|5|unsafe|all|
|-- unsafe|all
|HelloWorld_Trace|Hello World|3|TRACE|10000|5000|5000|unsafe|all|
|HelloWorld_Trace|Hello World|3|DEBUG|1000|500|500|unsafe|all|
|HelloWorld_Trace|Hello World|3|INFO|1000|500|500|unsafe|all|
|HelloWorld_Trace|Hello World|3|WARN|1000|500|500|unsafe|all|
|HelloWorld_Trace|Hello World|3|ERROR|1000|500|500|unsafe|all|
|--
|HelloWorld_Debug|Hello World|3|TRACE|0|0|5|unsafe|all|  
|HelloWorld_Debug|Hello World|3|DEBUG|1000|500|500|unsafe|all|
|HelloWorld_Debug|Hello World|3|INFO|1000|500|500|unsafe|all|
|HelloWorld_Debug|Hello World|3|WARN|1000|500|500|unsafe|all|
|HelloWorld_Debug|Hello World|3|ERROR|1000|500|500|unsafe|all|
|--
|HelloWorld_Info|Hello World|3|TRACE|0|0|5|unsafe|all|
|HelloWorld_Info|Hello World|3|DEBUG|0|0|5|unsafe|all|
|HelloWorld_Info|Hello World|3|INFO|1000|500|500|unsafe|all|
|HelloWorld_Info|Hello World|3|WARN|1000|500|500|unsafe|all|
|HelloWorld_Info|Hello World|3|ERROR|1000|500|500|unsafe|all|
|--
|HelloWorld_Warn|Hello World|3|TRACE|0|0|5|unsafe|all| 
|HelloWorld_Warn|Hello World|3|DEBUG|0|0|5|unsafe|all|
|HelloWorld_Warn|Hello World|3|INFO|0|0|5|unsafe|all|
|HelloWorld_Warn|Hello World|3|WARN|1000|500|500|unsafe|all|
|HelloWorld_Warn|Hello World|3|ERROR|1000|500|500|unsafe|all|
|--
|HelloWorld_Error|Hello World|3|TRACE|0|0|5|unsafe|all|
|HelloWorld_Error|Hello World|3|DEBUG|0|0|5|unsafe|all|
|HelloWorld_Error|Hello World|3|INFO|0|0|5|unsafe|all|
|HelloWorld_Error|Hello World|3|WARN|0|0|5|unsafe|all| 
|HelloWorld_Error|Hello World|3|ERROR|1000|500|500|unsafe|all|


Scenario: Simple Several Different Messages and safe|all [Set] Strategy

Given a matching strategy with <safety> and <reliability> and logger <logger> 
When I log <several> different messages made from <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|times|level|ignored|logged|several|safety|reliability|
|HelloWorld_Trace|Hello World|3|TRACE|10000|5000|5000|safe|all|
|HelloWorld_Trace|Hello World|3|DEBUG|1000|500|500|safe|all|
|HelloWorld_Trace|Hello World|3|INFO|1000|500|500|safe|all|
|HelloWorld_Trace|Hello World|3|WARN|1000|500|500|safe|all|
|HelloWorld_Trace|Hello World|3|ERROR|1000|500|500|safe|all|
|--
|HelloWorld_Debug|Hello World|3|TRACE|0|0|5|safe|all|  
|HelloWorld_Debug|Hello World|3|DEBUG|1000|500|500|safe|all|
|HelloWorld_Debug|Hello World|3|INFO|1000|500|500|safe|all|
|HelloWorld_Debug|Hello World|3|WARN|1000|500|500|safe|all|
|HelloWorld_Debug|Hello World|3|ERROR|1000|500|500|safe|all|
|--
|HelloWorld_Info|Hello World|3|TRACE|0|0|5|safe|all|
|HelloWorld_Info|Hello World|3|DEBUG|0|0|5|safe|all|
|HelloWorld_Info|Hello World|3|INFO|1000|500|500|safe|all|
|HelloWorld_Info|Hello World|3|WARN|1000|500|500|safe|all|
|HelloWorld_Info|Hello World|3|ERROR|1000|500|500|safe|all|
|--
|HelloWorld_Warn|Hello World|3|TRACE|0|0|5|safe|all| 
|HelloWorld_Warn|Hello World|3|DEBUG|0|0|5|safe|all|
|HelloWorld_Warn|Hello World|3|INFO|0|0|5|safe|all|
|HelloWorld_Warn|Hello World|3|WARN|1000|500|500|safe|all|
|HelloWorld_Warn|Hello World|3|ERROR|1000|500|500|safe|all|
|--
|HelloWorld_Error|Hello World|3|TRACE|0|0|5|safe|all|
|HelloWorld_Error|Hello World|3|DEBUG|0|0|5|safe|all|
|HelloWorld_Error|Hello World|3|INFO|0|0|5|safe|all|
|HelloWorld_Error|Hello World|3|WARN|0|0|5|safe|all| 
|HelloWorld_Error|Hello World|3|ERROR|1000|500|500|safe|all|


Scenario: Simple Several Different Messages and safe/most [Bloom] Strategy

Given a matching strategy with <safety> and <reliability> and logger <logger> 
When I log <several> different messages made from <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|times|level|ignored|logged|several|safety|reliability|
|HelloWorld_Trace|Hello World|3|TRACE|1000|500|500|safe|most|
|HelloWorld_Trace|Hello World|3|DEBUG|1000|500|500|safe|most|
|HelloWorld_Trace|Hello World|3|INFO|1000|500|500|safe|most|
|HelloWorld_Trace|Hello World|3|WARN|1000|500|500|safe|most|
|HelloWorld_Trace|Hello World|3|ERROR|1000|500|500|safe|most|
|--
|HelloWorld_Debug|Hello World|3|TRACE|0|0|5|safe|most|  
|HelloWorld_Debug|Hello World|3|DEBUG|1000|500|500|safe|most|
|HelloWorld_Debug|Hello World|3|INFO|1000|500|500|safe|most|
|HelloWorld_Debug|Hello World|3|WARN|1000|500|500|safe|most|
|HelloWorld_Debug|Hello World|3|ERROR|1000|500|500|safe|most|
|--
|HelloWorld_Info|Hello World|3|TRACE|0|0|5|safe|most|
|HelloWorld_Info|Hello World|3|DEBUG|0|0|5|safe|most|
|HelloWorld_Info|Hello World|3|INFO|1000|500|500|safe|most|
|HelloWorld_Info|Hello World|3|WARN|1000|500|500|safe|most|
|HelloWorld_Info|Hello World|3|ERROR|1000|500|500|safe|most|
|--
|HelloWorld_Warn|Hello World|3|TRACE|0|0|5|safe|most| 
|HelloWorld_Warn|Hello World|3|DEBUG|0|0|5|safe|most|
|HelloWorld_Warn|Hello World|3|INFO|0|0|5|safe|most|
|HelloWorld_Warn|Hello World|3|WARN|1000|500|500|safe|most|
|HelloWorld_Warn|Hello World|3|ERROR|1000|500|500|safe|most|
|--
|HelloWorld_Error|Hello World|3|TRACE|0|0|5|safe|most|
|HelloWorld_Error|Hello World|3|DEBUG|0|0|5|safe|most|
|HelloWorld_Error|Hello World|3|INFO|0|0|5|safe|most|
|HelloWorld_Error|Hello World|3|WARN|0|0|5|safe|most| 
|HelloWorld_Error|Hello World|3|ERROR|1000|500|500|safe|most|

Scenario: Simple Several Different Messages and unsafe/most [Bloom] Strategy

Given a matching strategy with <safety> and <reliability> and logger <logger> 
When I log <several> different messages made from <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|times|level|ignored|logged|several|safety|reliability|
|HelloWorld_Trace|Hello World|3|TRACE|1000|500|500|unsafe|most|
|HelloWorld_Trace|Hello World|3|DEBUG|1000|500|500|unsafe|most|
|HelloWorld_Trace|Hello World|3|INFO|1000|500|500|unsafe|most|
|HelloWorld_Trace|Hello World|3|WARN|1000|500|500|unsafe|most|
|HelloWorld_Trace|Hello World|3|ERROR|1000|500|500|unsafe|most|
|--
|HelloWorld_Debug|Hello World|3|TRACE|0|0|5|unsafe|most|  
|HelloWorld_Debug|Hello World|3|DEBUG|1000|500|500|unsafe|most|
|HelloWorld_Debug|Hello World|3|INFO|1000|500|500|unsafe|most|
|HelloWorld_Debug|Hello World|3|WARN|1000|500|500|unsafe|most|
|HelloWorld_Debug|Hello World|3|ERROR|1000|500|500|unsafe|most|
|--
|HelloWorld_Info|Hello World|3|TRACE|0|0|5|unsafe|most|
|HelloWorld_Info|Hello World|3|DEBUG|0|0|5|unsafe|most|
|HelloWorld_Info|Hello World|3|INFO|1000|500|500|unsafe|most|
|HelloWorld_Info|Hello World|3|WARN|1000|500|500|unsafe|most|
|HelloWorld_Info|Hello World|3|ERROR|1000|500|500|unsafe|most|
|--
|HelloWorld_Warn|Hello World|3|TRACE|0|0|5|unsafe|most| 
|HelloWorld_Warn|Hello World|3|DEBUG|0|0|5|unsafe|most|
|HelloWorld_Warn|Hello World|3|INFO|0|0|5|unsafe|most|
|HelloWorld_Warn|Hello World|3|WARN|1000|500|500|unsafe|most|
|HelloWorld_Warn|Hello World|3|ERROR|1000|500|500|unsafe|most|
|--
|HelloWorld_Error|Hello World|3|TRACE|0|0|5|unsafe|most|
|HelloWorld_Error|Hello World|3|DEBUG|0|0|5|unsafe|most|
|HelloWorld_Error|Hello World|3|INFO|0|0|5|unsafe|most|
|HelloWorld_Error|Hello World|3|WARN|0|0|5|unsafe|most| 
|HelloWorld_Error|Hello World|3|ERROR|1000|500|500|unsafe|most|
