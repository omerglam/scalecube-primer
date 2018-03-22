package quotes.service;


import rx.Observable;

import io.scalecube.services.annotations.Service;
import io.scalecube.services.annotations.ServiceMethod;

@Service
public interface QuotesService {

    @ServiceMethod
    Observable<Quote> quotes(QuoteRequest request);

}

