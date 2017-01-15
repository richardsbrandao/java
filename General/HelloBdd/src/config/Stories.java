package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import steps.CalculadoraSteps;

public class Stories extends BaseStories {

	@Override
	protected List<Object> createSteps() {
		List<Object> steps = new ArrayList<Object>();
		
		steps.add( new CalculadoraSteps() );
		
		return steps;
	}
	
	@Override
	public void run() throws Throwable {
		super.run();
	}

	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("stories/Calculadora.story");
	}	
}
