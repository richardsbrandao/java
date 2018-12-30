package productsearcher.services;

import productsearcher.FakeCall;
import productsearcher.TestConfig;
import productsearcher.models.Product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProductCatalogService {

    private final ExecutorService threadPoolForFutureBenchmark;

    public ProductCatalogService() {
        this.threadPoolForFutureBenchmark = Executors.newFixedThreadPool(5);
    }

    public Product syncFindByProductName(String productName) {
        return this.findByProductName(productName);
    }

    public Future<Product> futureFindByProductName(String productName) {
        return threadPoolForFutureBenchmark.submit(() -> this.findByProductName(productName));
    }

    public CompletableFuture<Product> completableFeatureFindByProductName(String productName) {
        return CompletableFuture.supplyAsync(
                () -> this.findByProductName(productName),
                TestConfig.threadPoolForCompletableFutureBenchmark);
    }

    private Product findByProductName(String productName) {
        return new FakeCall(
                        TestConfig.DEFAULT_LATENCY,
                "findByProductName").call(() -> new Product(1, productName));
    }
}
