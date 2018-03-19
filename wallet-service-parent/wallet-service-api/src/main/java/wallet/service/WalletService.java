package wallet.service;


import io.scalecube.services.annotations.Service;
import io.scalecube.services.annotations.ServiceMethod;

@Service
public interface WalletService
{
    @ServiceMethod
    BuyResponse buy(BuyRequest request);
}
