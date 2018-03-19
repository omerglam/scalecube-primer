package quotes.service;

public class Quote {

    String ticker;

    double bid;

    double ask;

    public Quote(String ticker, double bid, double ask) {
        this.ticker = ticker;
        this.bid = bid;
        this.ask = ask;
    }

    public String getTicker() {
        return ticker;
    }

    public double getBid() {

        return bid;
    }

    public double getAsk() {
        return ask;
    }
}
