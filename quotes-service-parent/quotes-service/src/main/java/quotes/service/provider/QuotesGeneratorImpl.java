package quotes.service.provider;

import quotes.service.Quote;
import rx.Emitter;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class QuotesGeneratorImpl implements QuotesGenerator {


    private String ticker;

    private Observable<Quote> quotesObservable;


    QuotesGeneratorImpl(String ticker) {
        System.out.println("new generator for " + ticker);
        this.ticker = ticker;
    }

    @Override
    public Observable<Quote> generate() {

        if(quotesObservable != null) {
            return quotesObservable;
        }

        int randomBaseRate = ThreadLocalRandom.current().nextInt(50, 100);

        Stream<Quote> tickerQuoteStream = Stream.iterate(new Quote(ticker, randomBaseRate - 1, randomBaseRate + 1),
                oldQuote -> {
                    double randomChange = ThreadLocalRandom.current().nextDouble(-1,1);
                    return new Quote(oldQuote.getTicker(),oldQuote.getBid() + randomChange,oldQuote.getAsk() + randomChange);
                });

        Observable<Quote> obs = Observable.from(tickerQuoteStream::iterator);

        quotesObservable = Observable.zip(obs, Observable.interval(1,TimeUnit.SECONDS),(quote,timer) -> quote).share();

        return quotesObservable;
    }
}
