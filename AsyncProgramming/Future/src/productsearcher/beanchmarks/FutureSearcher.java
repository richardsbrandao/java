package productsearcher.beanchmarks;

import productsearcher.models.CurrencyRates;
import productsearcher.models.Price;
import productsearcher.models.Product;
import productsearcher.services.ExchangeRateService;
import productsearcher.services.PriceFinderService;
import productsearcher.services.ProductCatalogService;

import java.util.concurrent.Future;

public class FutureSearcher {
    private final ProductCatalogService productCatalogService;
    private final PriceFinderService priceFinderService;
    private final ExchangeRateService exchangeRateService;

    public FutureSearcher() {
        this.productCatalogService = new ProductCatalogService();
        this.priceFinderService = new PriceFinderService();
        this.exchangeRateService = new ExchangeRateService();
    }

    public void findLocalPrice(CurrencyRates currency, String productName) throws Exception {
        CurrencyRates currencyBase = CurrencyRates.USD;
        long startTime = System.currentTimeMillis();

        Future<Product> productFuture = productCatalogService.futureFindByProductName(productName);
        Product product = productFuture.get();
        Future<Price> bestPriceFuture = priceFinderService.futureFindBestPrice(product);

        Future<Double> cambio = exchangeRateService.futureFindExchangeRate(currency, currencyBase);

        Price bestPrice = bestPriceFuture.get();
        Double priceInDesiredCurrency = calculatePrice(bestPrice, cambio.get());

        String feedbackMessage = "ProductName: %s - Price in %s: %.2f - Price in %s: %.2f";
        System.out.println(
                String.format(feedbackMessage,
                        product.getName(), currencyBase.name(), bestPrice.getPrice(),
                        currency.name(), priceInDesiredCurrency)
        );

        long endTime = System.currentTimeMillis();

        System.out.println("Future: That took " + (endTime - startTime) + " milliseconds");
    }

    private Double calculatePrice(Price bestPrice, Double cambio) {
        return bestPrice.getPrice() * cambio;
    }
}
