package quotes.service.provider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import quotes.service.Quote;

import java.util.ArrayList;
import java.util.List;

class QuotesGeneratorTest {

    @Before
    void SetUp(){

    }

    //TODO: get rid of Thread.Sleep (check rx unit testing capabilities [custom Scheduler?] )
    @Test
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
