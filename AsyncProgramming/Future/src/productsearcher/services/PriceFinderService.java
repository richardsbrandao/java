package productsearcher.services;

import productsearcher.FakeCall;
import productsearcher.TestConfig;
import productsearcher.models.Price;
import productsearcher.models.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class PriceFinderService {
    private final Map<String, Price> prices;

    public PriceFinderService() {
        this.prices = new HashMap<String, Price>() {{
            put("IPhone 10", new Price("Amazon", 900D));
        }};
    }

    public Price syncFindBestPrice(Product product) {
        return this.findBestPrice(product);
    }

    public Future<Price> futureFindBestPrice(Product product) {
        return TestConfig.threadPoolForFutureBenchmark.submit(() -> this.findBestPrice(product));
    }

    public Price findBestPrice(Product product) {
        return new FakeCall(TestConfig.DEFAULT_LATENCY, "findBestPrice")
                .call(() -> this.prices.get(product.getName()));
    }

    public CompletableFuture<Price> completableFeatureFindBestPrice(Product product) {
        return CompletableFuture.supplyAsync(() -> this.findBestPrice(product), TestConfig.threadPoolForCompletableFutureBenchmark);
    }
}
