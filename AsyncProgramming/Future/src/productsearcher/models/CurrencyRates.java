package productsearcher.models;

public enum CurrencyRates {
    EUR(1.00, 0.23, 0.88),
    BRL(4.41, 0.00, 3.85),
    USD(1.13, 0.26, 1.00);

    private Double toEuro;
    private Double toReal;
    private Double toDollar;

    CurrencyRates(Double toEuro, Double toReal, Double toDollar) {
        this.toEuro = toEuro;
        this.toReal = toReal;
        this.toDollar = toDollar;
    }

    public Double getToEuro() {
        return toEuro;
    }

    public Double getToReal() {
        return toReal;
    }

    public Double getToDollar() {
        return toDollar;
    }

    public Double from(CurrencyRates currency) {
        String methodName = getMethodName(currency);
        try {
            return (Double) this.getClass().getMethod(methodName).invoke(this);
        } catch (Exception e) {
            return 0.;
        }
    }

    private String getMethodName(CurrencyRates currency) {
        if(currency.equals(EUR)) {
            return "getToEuro";
        } else if(currency.equals(USD)) {
            return "getToDollar";
        } else {
            return "getToReal";
        }
    }
}
