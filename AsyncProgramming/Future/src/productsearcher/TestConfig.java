package productsearcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConfig {
    public static final int DEFAULT_LATENCY = 1000;
    public static final Boolean DEBUG = Boolean.TRUE;
    public static final ExecutorService threadPoolForFutureBenchmark = Executors.newFixedThreadPool(5);
    public static final ExecutorService threadPoolForCompletableFutureBenchmark = Executors.newFixedThreadPool(5);
}
