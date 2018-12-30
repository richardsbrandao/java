package productsearcher.models;

public class Price {
    private String store;
    private Double price;

    public Price(String store, double price) {
        this.store = store;
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "store='" + store + '\'' +
                ", price=" + price +
                '}';
    }
}
