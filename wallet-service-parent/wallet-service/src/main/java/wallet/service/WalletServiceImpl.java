package wallet.service;

import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;

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
                .services(WalletService.class)
                .build();

        System.out.println(ms.cluster().members());
    }

    @Override
    public BuyResponse buy(BuyRequest request) {

        return new BuyResponse(true);
    }
}
