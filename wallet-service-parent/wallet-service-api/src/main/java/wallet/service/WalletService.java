package wallet.service;


import io.scalecube.services.annotations.Service;
import io.scalecube.services.annotations.ServiceMethod;

import java.util.concurrent.CompletableFuture;

@Service
public interface WalletService
{
    @ServiceMethod
    CompletableFuture<BuyResponse> buy(BuyRequest request);
}
