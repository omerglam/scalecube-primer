package wallet.service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WalletServiceImpl implements WalletService
{
    ConcurrentMap<Integer,Double> ledger = new ConcurrentHashMap<>();

    public static void main( String[] args )
    {
        System.out.println("Hello World!");
    }

    @Override
    public BuyResponse buy(BuyRequest request) {

        return new BuyResponse(true);
    }
}
