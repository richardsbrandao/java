package stringreader;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class MergeArrayTask extends RecursiveTask<String> {

	private static final Integer THREADSHOLD = 10;
	private String[] text;
	private int start;
	private int end;

	public MergeArrayTask(String[] text) {
		this(text, 0, text.length);
	}
	
	public MergeArrayTask(String text[], int start, int end) {
		this.text = text;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected String compute() {
		int range = end - start;
		if(range < THREADSHOLD) {
			StringBuilder string = new StringBuilder();
			for(int i = start; i < end; i++) {
				string.append(text[i]);
			}
			return string.toString();
		} else {
			int limit = range / 2;
			MergeArrayTask left = new MergeArrayTask(text, start, start+limit);
			ForkJoinTask<String> leftTask = left.fork();
			MergeArrayTask right = new MergeArrayTask(text, start+limit, end);
			
			String rightResult = right.compute();
			String leftResult = leftTask.join();
			
			return leftResult + rightResult;
		}
	}

}
