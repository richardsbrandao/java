package config;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML_TEMPLATE;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.ConsoleOutput;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.junit.runner.RunWith;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public abstract class BaseStories extends JUnitStories {

	public BaseStories() {
		configuredEmbedder().embedderControls()
			.doGenerateViewAfterStories(true)
			.doIgnoreFailureInStories(false)
			.doIgnoreFailureInView(true)
			.doVerboseFailures(true)
			.useThreads(2);
	}

	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		
		Properties viewResources = new Properties();
		viewResources.put("decorateNonHtml", "false");
		viewResources.put("encoding", "UTF-8");
		
		ParameterConverters parameterConverters = new ParameterConverters();
		
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters, new TableTransformers());

		parameterConverters.addConverters(
				new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
				new ExamplesTableConverter(examplesTableFactory)
		);
		
		// Comandos em Portugues
		Keywords keywords = new LocalizedKeywords(new Locale("pt", "BR"));
		
		return new MostUsefulConfiguration()
						.useKeywords(keywords)
						.useParanamer(new CachingParanamer(new BytecodeReadingParanamer()))
						.usePendingStepStrategy(new FailingUponPendingStep()) // Default é não falhar
						.useStoryLoader(new LoadFromClasspath(embeddableClass))
						.useStoryParser(new RegexStoryParser(examplesTableFactory))
						.useStoryReporterBuilder(new StoryReporterBuilder()
								.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withViewResources(viewResources)
								.withFormats(CONSOLE, HTML_TEMPLATE)
								.withFailureTrace(true)
								.withFailureTraceCompression(true))
						.useDefaultStoryReporter(new ConsoleOutput())
						.useParameterConverters(parameterConverters)
						.useStepPatternParser(new RegexPrefixCapturingPatternParser("$"));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), createSteps());
	}

	abstract protected List<Object> createSteps();
	
}