package quotes.service.provider;

import quotes.service.Quote;
import rx.Observable;

/**
 * Hello world!
 *
 */
public interface QuotesGenerator
{
    Observable<Quote> generate();
}
