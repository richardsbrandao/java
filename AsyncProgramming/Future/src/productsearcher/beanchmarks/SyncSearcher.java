package productsearcher.beanchmarks;

import productsearcher.models.CurrencyRates;
import productsearcher.models.Price;
import productsearcher.models.Product;
import productsearcher.services.ExchangeRateService;
import productsearcher.services.PriceFinderService;
import productsearcher.services.ProductCatalogService;

public class SyncSearcher {
    private final ProductCatalogService productCatalogService;
    private final PriceFinderService priceFinderService;
    private final ExchangeRateService exchangeRateService;

    public SyncSearcher() {
        this.productCatalogService = new ProductCatalogService();
        this.priceFinderService = new PriceFinderService();
        this.exchangeRateService = new ExchangeRateService();
    }

    public void findLocalPrice(CurrencyRates currency, String productName) {
        CurrencyRates currencyBase = CurrencyRates.USD;
        long startTime = System.currentTimeMillis();

        Product product = productCatalogService.syncFindByProductName(productName);
        Price bestPrice = priceFinderService.syncFindBestPrice(product);

        Double cambio = exchangeRateService.syncFindExchangeRate(currency, currencyBase);

        Double priceInDesiredCurrency = calculatePrice(bestPrice, cambio);

        String feedbackMessage = "ProductName: %s - Price in %s: %.2f - Price in %s: %.2f";
        System.out.println(
                String.format(feedbackMessage,
                        product.getName(), currencyBase.name(), bestPrice.getPrice(),
                        currency.name(), priceInDesiredCurrency)
        );

        long endTime = System.currentTimeMillis();

        System.out.println("SYNC: That took " + (endTime - startTime) + " milliseconds");
    }

    private Double calculatePrice(Price bestPrice, Double cambio) {
        return bestPrice.getPrice() * cambio;
    }
}
