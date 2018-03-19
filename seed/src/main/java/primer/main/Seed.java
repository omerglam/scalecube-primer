package primer.main;

import io.scalecube.services.Microservices;
import quotes.service.provider.QuotesServiceImpl;


public class Seed
{
    public static void main( String[] args )
    {
        Microservices seed = Microservices.builder()
                .build();

        System.out.println(seed.cluster().address());
    }
}
