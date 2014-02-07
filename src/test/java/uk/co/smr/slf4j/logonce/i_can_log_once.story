Scenario: Simple Message

Given a logger <logger> 
When I log one message <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|times|level|ignored|logged|
|Any|Hello World|3|TRACE|2|1|    
|--
|HelloWorld_Trace|Hello World|3|TRACE|2|1|    
|HelloWorld_Trace|Hello World|3|DEBUG|2|1|    
|HelloWorld_Trace|Hello World|3|INFO|2|1|    
|HelloWorld_Trace|Hello World|3|WARN|2|1|    
|HelloWorld_Trace|Hello World|3|ERROR|2|1|    
|--
|HelloWorld_Debug|Hello World|3|TRACE|0|0|    
|HelloWorld_Debug|Hello World|3|DEBUG|2|1|  
|HelloWorld_Debug|Hello World|3|INFO|2|1|    
|HelloWorld_Debug|Hello World|3|WARN|2|1|    
|HelloWorld_Debug|Hello World|3|ERROR|2|1|    
|--
|HelloWorld_Info|Hello World|3|TRACE|0|0|    
|HelloWorld_Info|Hello World|3|DEBUG|0|0|  
|HelloWorld_Info|Hello World|3|INFO|2|1|    
|HelloWorld_Info|Hello World|3|WARN|2|1|    
|HelloWorld_Info|Hello World|3|ERROR|2|1|    
|--
|HelloWorld_Warn|Hello World|3|TRACE|0|0|    
|HelloWorld_Warn|Hello World|3|DEBUG|0|0|  
|HelloWorld_Warn|Hello World|3|INFO|0|0|    
|HelloWorld_Warn|Hello World|3|WARN|2|1|    
|HelloWorld_Warn|Hello World|3|ERROR|2|1|    
|--
|HelloWorld_Error|Hello World|3|TRACE|0|0|    
|HelloWorld_Error|Hello World|3|DEBUG|0|0|  
|HelloWorld_Error|Hello World|3|INFO|0|0|    
|HelloWorld_Error|Hello World|3|WARN|0|0|    
|HelloWorld_Error|Hello World|3|ERROR|2|1|    


Scenario: One Parameter Message

Given a logger <logger>
When I log a formatted message <message> with one parameter <parameter> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|parameter|times|level|ignored|logged|
|Any|Hello {}|World|3|TRACE|2|1|    
|--
|HelloWorld_Trace|Hello {}|World|3|TRACE|2|1|    
|HelloWorld_Trace|Hello {}|World|3|DEBUG|2|1|    
|HelloWorld_Trace|Hello {}|World|3|INFO|2|1|    
|HelloWorld_Trace|Hello {}|World|3|WARN|2|1|    
|HelloWorld_Trace|Hello {}|World|3|ERROR|2|1|    
|--
|HelloWorld_Debug|Hello {}|World|3|TRACE|0|0|    
|HelloWorld_Debug|Hello {}|World|3|DEBUG|2|1|  
|HelloWorld_Debug|Hello {}|World|3|INFO|2|1|    
|HelloWorld_Debug|Hello {}|World|3|WARN|2|1|    
|HelloWorld_Debug|Hello {}|World|3|ERROR|2|1|    
|--
|HelloWorld_Info|Hello {}|World|3|TRACE|0|0|    
|HelloWorld_Info|Hello {}|World|3|DEBUG|0|0|  
|HelloWorld_Info|Hello {}|World|3|INFO|2|1|    
|HelloWorld_Info|Hello {}|World|3|WARN|2|1|    
|HelloWorld_Info|Hello {}|World|3|ERROR|2|1|    
|--
|HelloWorld_Warn|Hello {}|World|3|TRACE|0|0|    
|HelloWorld_Warn|Hello {}|World|3|DEBUG|0|0|  
|HelloWorld_Warn|Hello {}|World|3|INFO|0|0|    
|HelloWorld_Warn|Hello {}|World|3|WARN|2|1|    
|HelloWorld_Warn|Hello {}|World|3|ERROR|2|1|    
|--
|HelloWorld_Error|Hello {}|World|3|TRACE|0|0|    
|HelloWorld_Error|Hello {}|World|3|DEBUG|0|0|  
|HelloWorld_Error|Hello {}|World|3|INFO|0|0|    
|HelloWorld_Error|Hello {}|World|3|WARN|0|0|    
|HelloWorld_Error|Hello {}|World|3|ERROR|2|1|    


Scenario: Two Parameter Message

Given a logger <logger>
When I log a formatted message <message> with two parameters <parameterOne> and <parameterTwo> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|parameterOne|parameterTwo|times|level|ignored|logged|
|Any|Hello {}{}|Wor|ld|3|TRACE|2|1|    
|--
|HelloWorld_Trace|Hello {}{}|Wor|ld|3|TRACE|2|1|    
|HelloWorld_Trace|Hello {}{}|Wor|ld|3|DEBUG|2|1|    
|HelloWorld_Trace|Hello {}{}|Wor|ld|3|INFO|2|1|    
|HelloWorld_Trace|Hello {}{}|Wor|ld|3|WARN|2|1|    
|HelloWorld_Trace|Hello {}{}|Wor|ld|3|ERROR|2|1|    
|--
|HelloWorld_Debug|Hello {}{}|Wor|ld|3|TRACE|0|0|    
|HelloWorld_Debug|Hello {}{}|Wor|ld|3|DEBUG|2|1|  
|HelloWorld_Debug|Hello {}{}|Wor|ld|3|INFO|2|1|    
|HelloWorld_Debug|Hello {}{}|Wor|ld|3|WARN|2|1|    
|HelloWorld_Debug|Hello {}{}|Wor|ld|3|ERROR|2|1|    
|--
|HelloWorld_Info|Hello {}{}|Wor|ld|3|TRACE|0|0|    
|HelloWorld_Info|Hello {}{}|Wor|ld|3|DEBUG|0|0|  
|HelloWorld_Info|Hello {}{}|Wor|ld|3|INFO|2|1|    
|HelloWorld_Info|Hello {}{}|Wor|ld|3|WARN|2|1|    
|HelloWorld_Info|Hello {}{}|Wor|ld|3|ERROR|2|1|    
|--
|HelloWorld_Warn|Hello {}{}|Wor|ld|3|TRACE|0|0|    
|HelloWorld_Warn|Hello {}{}|Wor|ld|3|DEBUG|0|0|  
|HelloWorld_Warn|Hello {}{}|Wor|ld|3|INFO|0|0|    
|HelloWorld_Warn|Hello {}{}|Wor|ld|3|WARN|2|1|    
|HelloWorld_Warn|Hello {}{}|Wor|ld|3|ERROR|2|1|    
|--
|HelloWorld_Error|Hello {}{}|Wor|ld|3|TRACE|0|0|    
|HelloWorld_Error|Hello {}{}|Wor|ld|3|DEBUG|0|0|  
|HelloWorld_Error|Hello {}{}|Wor|ld|3|INFO|0|0|    
|HelloWorld_Error|Hello {}{}|Wor|ld|3|WARN|0|0|    
|HelloWorld_Error|Hello {}{}|Wor|ld|3|ERROR|2|1|    

Scenario: Simple Several Different Messages

Given a logger <logger> 
When I log <several> different messages made from <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>
And logger should contain Hello World at <level> if logged <logged> is greater than zero

Examples: 
|logger|message|times|level|ignored|logged|several|
|Any|Hello World|3|TRACE|10|5|5|
|--
|HelloWorld_Trace|Hello World|3|TRACE|10|5|5|    
|HelloWorld_Trace|Hello World|3|DEBUG|10|5|5|    
|HelloWorld_Trace|Hello World|3|INFO|10|5|5|    
|HelloWorld_Trace|Hello World|3|WARN|10|5|5|    
|HelloWorld_Trace|Hello World|3|ERROR|10|5|5|    
|--
|HelloWorld_Debug|Hello World|3|TRACE|0|0|5|   
|HelloWorld_Debug|Hello World|3|DEBUG|10|5|5|  
|HelloWorld_Debug|Hello World|3|INFO|10|5|5|    
|HelloWorld_Debug|Hello World|3|WARN|10|5|5|    
|HelloWorld_Debug|Hello World|3|ERROR|10|5|5|    
|--
|HelloWorld_Info|Hello World|3|TRACE|0|0|5| 
|HelloWorld_Info|Hello World|3|DEBUG|0|0|5|
|HelloWorld_Info|Hello World|3|INFO|10|5|5|    
|HelloWorld_Info|Hello World|3|WARN|10|5|5|    
|HelloWorld_Info|Hello World|3|ERROR|10|5|5|    
|--
|HelloWorld_Warn|Hello World|3|TRACE|0|0|5|  
|HelloWorld_Warn|Hello World|3|DEBUG|0|0|5|
|HelloWorld_Warn|Hello World|3|INFO|0|0|5| 
|HelloWorld_Warn|Hello World|3|WARN|10|5|5|    
|HelloWorld_Warn|Hello World|3|ERROR|10|5|5|    
|--
|HelloWorld_Error|Hello World|3|TRACE|0|0|5|    
|HelloWorld_Error|Hello World|3|DEBUG|0|0|5|
|HelloWorld_Error|Hello World|3|INFO|0|0|5| 
|HelloWorld_Error|Hello World|3|WARN|0|0|5|  
|HelloWorld_Error|Hello World|3|ERROR|10|5|5|    
