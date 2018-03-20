package wallet.service;

import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WalletServiceImpl implements WalletService
{
    ConcurrentMap<Integer,Double> ledger = new ConcurrentHashMap<>();

    public static void main( String[] args )
    {
        Address seedAddress = Address.create("172.28.29.65",4802);

        Microservices ms =  Microservices.builder()
                .seeds(seedAddress)
                .services(new WalletServiceImpl())
                .build();

        System.out.println(ms.cluster().members());
    }

    @Override
    public CompletableFuture<BuyResponse> buy(BuyRequest request) {
        System.out.println("received buy request");

        return CompletableFuture.completedFuture(new BuyResponse(true));
    }
}
