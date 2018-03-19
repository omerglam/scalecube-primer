package quotes.service.provider;

import quotes.service.Quote;
import quotes.service.QuoteRequest;
import quotes.service.QuotesService;
import rx.Observable;
import rx.subjects.PublishSubject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class QuotesServiceImpl implements QuotesService {

    private List<String> tickers = Arrays.asList("USDJPY", "EURJPY"); // TODO: take from config

    private ConcurrentMap<String, QuotesGenerator> quotesGenerators = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        //TODO: scalecube bootstraping goes here.


    }

    @Override
    public Observable<Quote> quotes(QuoteRequest request) {

        // if already serving the quote, reuse the stream
        if (quotesGenerators.containsKey(request.getTicker())) {

            return quotesGenerators.get(request.getTicker()).generate(); //TODO: ugly coupling with internal impl - change
        }

        QuotesGenerator generator = new QuotesGeneratorImpl(request.getTicker());

        return generator.generate();
    }
}
