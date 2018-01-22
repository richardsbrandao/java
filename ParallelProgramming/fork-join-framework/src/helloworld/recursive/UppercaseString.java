package helloworld.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class UppercaseString extends RecursiveAction {

	private String string;
	private static final int THREADSHOLD = 4;
	
	public UppercaseString(String string) {
		this.string  = string;
	}
	
	@Override
	protected void compute() {
		if(string.length() > THREADSHOLD) {
			ForkJoinTask.invokeAll(createSubtasks());
		} else {
			process(string);
		}
	}

	private List<UppercaseString> createSubtasks() {
		List<UppercaseString> subtasks = new ArrayList<UppercaseString>();
		String partOne = string.substring(0, string.length()/2);
		String partTwo = string.substring(string.length()/2);
		subtasks.add(new UppercaseString(partOne));
		subtasks.add(new UppercaseString(partTwo));
		return subtasks;
	}

	private void process(String string) {
		String result = string.toUpperCase();
		System.out.println(result);
	}

}
