package uk.co.smr.slf4j.logonce;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class LogOnceStories extends JUnitStories {
 
    // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
//        	.usePendingStepStrategy(new FailingUponPendingStep())
            // where to find the stories
            .useStoryLoader(new LoadFromClasspath(this.getClass()))  
//            // CONSOLE and TXT reporting
            .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().
            		withFormats(Format.CONSOLE, Format.TXT))
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
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "");
    }

}