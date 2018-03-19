package wallet.service;

public class BuyRequest {

    int buyerIdentifier;
    int amount;

    public BuyRequest(int buyerIdentifier, int amount) {
        this.buyerIdentifier = buyerIdentifier;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getBuyerIdentifier() {
        return buyerIdentifier;
    }
}
