package wallet.service;


import io.scalecube.services.Microservices;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class WalletServiceImplTest
{
    @Test
    public void walletService_callBuy_success(){
        Microservices seed = Microservices.builder()
                .build();

        Microservices ms =  Microservices.builder()
                .seeds(seed.cluster().address())
                .services(new WalletServiceImpl())
                .build();

        WalletService serviceProxy =  ms.proxy().api(WalletService.class).create();

        CompletableFuture<BuyResponse> response = serviceProxy.buy(new BuyRequest(1,100));

        Assert.assertTrue(response.getNow(null).isSuccessfull);
    }
}
