package wallet.service;

public class BuyResponse {

    Boolean isSuccessfull;

    public BuyResponse(Boolean isSuccessfull) {

        this.isSuccessfull = isSuccessfull;
    }

    public Boolean getSuccessfull() {
        return isSuccessfull;
    }
}
