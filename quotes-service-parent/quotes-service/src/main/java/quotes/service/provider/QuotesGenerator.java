package quotes.service.provider;

import quotes.service.Quote;
import rx.Observable;


public interface QuotesGenerator
{
    Observable<Quote> generate();
}
