package quotes.service;

public class QuoteRequest {

    String ticker;

    public QuoteRequest(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }
}
