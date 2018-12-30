package productsearcher;

import productsearcher.beanchmarks.CompletableFutureSearcher;
import productsearcher.beanchmarks.FutureSearcher;
import productsearcher.beanchmarks.SyncSearcher;
import productsearcher.models.CurrencyRates;

public class Searcher {
    public static void main(String[] args) throws Exception {
        CurrencyRates currency = CurrencyRates.EUR;
        String productName = "IPhone 10";

        SyncSearcher syncSearcher = new SyncSearcher();
        syncSearcher.findLocalPrice(currency, productName);
        System.out.println("-------------------------");

        FutureSearcher futureSearcher = new FutureSearcher();
        futureSearcher.findLocalPrice(currency, productName);
        System.out.println("-------------------------");

        CompletableFutureSearcher completableFutureSearcher = new CompletableFutureSearcher();
        completableFutureSearcher.findLocalPrice(currency, productName);
        System.out.println("-------------------------");

        TestConfig.threadPoolForCompletableFutureBenchmark.shutdown();
        TestConfig.threadPoolForFutureBenchmark.shutdown();
    }
}
