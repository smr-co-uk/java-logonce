package uk.co.smr.slf4j.logonce;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;


//@RunWith(JUnitReportingRunner.class)
//@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true,
//	ignoreFailureInStories = true, ignoreFailureInView = false,  verboseFailures = true)
public class LogOnceStories extends JUnitStories {
    // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
        	.usePendingStepStrategy(new FailingUponPendingStep())
            // where to find the stories
            .useStoryLoader(new LoadFromClasspath(this.getClass()))  
            // CONSOLE and TXT reporting
            .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().
            		withFormats(Format.CONSOLE, Format.TXT, Format.XML, Format.HTML))
            ; 
    }
 
    // Here we specify the steps classes
    @Override
    public InjectableStepsFactory stepsFactory() {        
        // varargs, can have more that one steps classes
        return new InstanceStepsFactory(configuration(), new LogOnceSteps());
    }
    
    @Override
    protected List<String> storyPaths() {
    	return Arrays.asList(
    			"uk/co/smr/slf4j/logonce/log_once_using_all_filter_strategies.story",
    			"uk/co/smr/slf4j/logonce/log_once_using_default_filter_strategy.story");
//        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "");
    }

//    @Test
//    public void run() throws Throwable {
//        throw new UnsupportedOperationException("run");
//    }

}