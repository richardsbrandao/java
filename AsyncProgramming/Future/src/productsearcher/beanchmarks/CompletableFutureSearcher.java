package productsearcher.beanchmarks;

import productsearcher.models.CurrencyRates;
import productsearcher.models.Price;
import productsearcher.models.Product;
import productsearcher.services.ExchangeRateService;
import productsearcher.services.PriceFinderService;
import productsearcher.services.ProductCatalogService;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSearcher {
    private final ProductCatalogService productCatalogService;
    private final PriceFinderService priceFinderService;
    private final ExchangeRateService exchangeRateService;

    public CompletableFutureSearcher() {
        this.productCatalogService = new ProductCatalogService();
        this.priceFinderService = new PriceFinderService();
        this.exchangeRateService = new ExchangeRateService();
    }

    public void findLocalPrice(CurrencyRates currency, String productName) {
        CurrencyRates currencyBase = CurrencyRates.USD;
        long startTime = System.currentTimeMillis();

        findByProductName(productName)
                .thenCompose(this::findBestPrice)
                .thenCombine(this.findExchangeRate(currency, currencyBase), this::calculatePrice)
                .thenAccept(localPrice -> {
                    String feedbackMessage = "ProductName: %s - Price in %s: %.2f - Price in %s: %.2f";
                    System.out.println(
                            String.format(feedbackMessage,
                                    productName, currencyBase.name(), 0., // how to get??
                                    currency.name(), localPrice)
                    );
                }).join();

        long endTime = System.currentTimeMillis();

        System.out.println("CompletableFeature: That took " + (endTime - startTime) + " milliseconds");
    }

    private CompletableFuture<Product> findByProductName(String productName) {
        return productCatalogService.completableFeatureFindByProductName(productName);
    }

    private CompletableFuture<Price> findBestPrice(Product product) {
        return priceFinderService.completableFeatureFindBestPrice(product);
    }

    private CompletableFuture<Double> findExchangeRate(CurrencyRates to, CurrencyRates from) {
        return exchangeRateService.completableFeatureFindExchangeRate(to, from);
    }

    private Double calculatePrice(Price bestPrice, Double cambio) {
        return bestPrice.getPrice() * cambio;
    }
}
