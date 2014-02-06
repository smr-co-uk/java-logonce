Scenario: Simple Message

Given a logger <logger> 
When I log one message <message> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>

Examples: 
|logger|message|times|level|ignored|logged|
|Any|hello|3|TRACE|2|1|    
|HelloWorld_Trace|Hello World|3|TRACE|2|1|    
|HelloWorld_Debug|Hello World|3|TRACE|0|0|    
|HelloWorld_Debug|Hello World|3|DEBUG|2|1|    


Scenario: One Parameter Message

Given a logger <logger>
When I log a formatted message <message> with one parameter <parameter> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>

Examples: 
|logger|message|parameter|times|level|ignored|logged|
|Any|hello {}|world|3|TRACE|2|1|    
|HelloWorld_Trace|hello {}|world|3|TRACE|2|1|    
|HelloWorld_Debug|hello {}|world|3|TRACE|0|0|    
|HelloWorld_Debug|hello {}|world|3|DEBUG|2|1|    


Scenario: Two Parameter Message

Given a logger <logger>
When I log a formatted message <message> with two parameters <parameterOne> and <parameterTwo> for <times> times at level <level> 
Then ignored should equal <ignored> and logged should equal <logged>

Examples: 
|logger|message|parameterOne|parameterTwo|times|level|ignored|logged|
|Any|hello {} {}|world|yipee|3|TRACE|2|1|    
|HelloWorld_Trace|hello {} {}|world|yipee|3|TRACE|2|1|    
|HelloWorld_Debug|hello {} {}|world|yipee|3|TRACE|0|0|    
|HelloWorld_Debug|hello {} {}|world|yipee|3|DEBUG|2|1|    

