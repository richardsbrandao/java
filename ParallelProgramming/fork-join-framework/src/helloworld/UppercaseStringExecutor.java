package helloworld;

import java.util.concurrent.ForkJoinPool;

import helloworld.recursive.UppercaseString;

public class UppercaseStringExecutor {

	public static void main(String[] args) {
		System.out.println("Parallelism " + ForkJoinPool.getCommonPoolParallelism());
		ForkJoinPool pool = ForkJoinPool.commonPool();
		UppercaseString action = new UppercaseString(example());
		pool.invoke(action);
	}

	private static String example() {
		return "Pedro de Alcantara Joao Carlos Leopoldo Salvador Bibiano Francisco Xavier de Paula Leocadio Miguel Gabriel Rafael Gonzaga";
	}
	
}
