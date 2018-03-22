package quotes.service.provider;

import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import quotes.service.Quote;
import quotes.service.QuoteRequest;
import quotes.service.QuotesService;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

class QuotesGeneratorTest {

    @Before
    void SetUp(){

    }
    @Test
    void quotes_quotesGeneration_success(){

        Microservices seed = Microservices.builder()
                .build();

        Microservices ms =  Microservices.builder()
                .seeds(seed.cluster().address())
                .services(new QuotesServiceImpl())
                .build();

        QuotesService serviceProxy =  ms.proxy().api(QuotesService.class).create();

        Observable<Quote> quotes = serviceProxy.quotes(new QuoteRequest("USDJPY"));


        List<Quote> quotesReceived = new ArrayList<>();

        quotes.doOnNext(quote -> System.out.println(quote.getTicker())).subscribe(quotesReceived::add);

        try {

            Thread.sleep(2300);
            Assert.assertTrue(quotesReceived.size() >= 2);

        }catch (Exception e){
            Assert.assertTrue("Exception thrown",false);
        }
    }


    //TODO: get rid of Thread.Sleep (check rx unit testing capabilities [custom Scheduler?] )
    @Test
    @Ignore
    void Generate_QuoteIsGeneratedEverySecond_Success(){
        QuotesGeneratorImpl sut = new QuotesGeneratorImpl("USDJPY");

        List<Quote> quotesReceived = new ArrayList<>();

        sut.generate().subscribe(quotesReceived::add);

        try {

            Thread.sleep(2300);
            Assert.assertTrue(quotesReceived.size() >= 2);

        }catch (Exception e){
            Assert.assertTrue("Exception thrown",false);
        }

    }
}
