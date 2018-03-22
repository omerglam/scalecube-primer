package gateway.main;

import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import quotes.service.QuoteRequest;
import quotes.service.QuotesService;
import rx.Subscription;
import wallet.service.BuyRequest;
import wallet.service.WalletService;


public class Gateway {
    public static void main(String[] args) {

        Address seedAddress = Address.create("172.28.29.65", 4802);

        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        router.route("/*").handler(StaticHandler.create());

        router.route("/buy").method(HttpMethod.POST).handler(routingContext -> {
//            JsonObject body = routingContext.getBodyAsJson();
            routingContext.request().bodyHandler(body -> {
                JsonObject jsonObject = new JsonObject(body.toString());

                Microservices ms = Microservices.builder().seeds(seedAddress).build();
                WalletService walletService = ms.proxy().api(WalletService.class).create();
                walletService.buy(new BuyRequest(jsonObject.getInteger("buyerIdentifer"), jsonObject.getInteger("amount"))).thenAccept(buyResponse -> {
                    routingContext.response().setStatusCode(200).end(Json.encodePrettily(buyResponse));
                });
            });

        });

        //TODO: here or inside the handler??
        Microservices microservices = Microservices.builder().seeds(seedAddress).build();
        QuotesService quotesService = microservices.proxy().api(QuotesService.class).create();

        vertx.createHttpServer().websocketHandler(webSocket -> {

            String path = webSocket.path().replaceAll("/","");

            Subscription sub = quotesService.quotes(new QuoteRequest("USDJPY"))
                    .subscribe(quote -> webSocket.writeTextMessage(Json.encode(quote)));

            Handler<Void> unsubscribe = (nothing) -> sub.unsubscribe();
            webSocket.closeHandler(unsubscribe);
            webSocket.endHandler(unsubscribe);
            webSocket.exceptionHandler(throwable -> {
                unsubscribe.handle(null);

                System.out.println(throwable);
            });

        }).requestHandler(router::accept).listen(8080);
    }
}
