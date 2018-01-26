package filesearch;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class FileSearchExecutor {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//String dir = System.getProperty("user.dir");
		String dir = "/home/richard/Projetos";
		FileSearch fileSearch = new FileSearch(dir, "txt");
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		
		LocalDateTime now = LocalDateTime.now();
		forkJoinPool.submit(() -> fileSearch.compute()).get();
		LocalDateTime end = LocalDateTime.now();

		System.out.println(now.toString());
		System.out.println(end.toString());
	}
}
