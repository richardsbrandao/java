package productsearcher.services;

import productsearcher.FakeCall;
import productsearcher.TestConfig;
import productsearcher.models.CurrencyRates;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ExchangeRateService {
    public Double syncFindExchangeRate(CurrencyRates to, CurrencyRates from) {
        return new FakeCall(TestConfig.DEFAULT_LATENCY, "syncFindExchangeRate")
                        .call(() -> to.from(from));
    }

    public Future<Double> futureFindExchangeRate(CurrencyRates to, CurrencyRates from) {
        return TestConfig.threadPoolForFutureBenchmark.submit(() -> this.findExchangeRate(to, from));
    }

    public Double findExchangeRate(CurrencyRates to, CurrencyRates from) {
        return new FakeCall(TestConfig.DEFAULT_LATENCY, "findExchangeRate")
                .call(() -> to.from(from));
    }

    public CompletableFuture<Double> completableFeatureFindExchangeRate(CurrencyRates to, CurrencyRates from) {
        return CompletableFuture.supplyAsync(() -> findExchangeRate(to, from), TestConfig.threadPoolForCompletableFutureBenchmark);
    }
}
