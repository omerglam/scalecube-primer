package quotes.service.provider;

import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;
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

        Address seedAddress = Address.create("172.28.29.65", 4802);

        Microservices ms = Microservices.builder()
                .seeds(seedAddress)
                .services(new QuotesServiceImpl())
                .build();

        System.out.println(ms.cluster().members());
    }

    @Override
    public Observable<Quote> quotes(QuoteRequest request) {

        //TODO: requires synchronization over the quotes generator collection?

        // if already serving the quote, reuse the stream
        if (quotesGenerators.containsKey(request.getTicker())) {

            return quotesGenerators.get(request.getTicker()).generate(); //TODO: ugly coupling with internal impl - change
        }

        QuotesGenerator generator = new QuotesGeneratorImpl(request.getTicker());

        quotesGenerators.putIfAbsent(request.getTicker(), generator);

        return generator.generate();
    }
}
