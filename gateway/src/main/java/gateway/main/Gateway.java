package gateway.main;

import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import wallet.service.BuyRequest;
import wallet.service.WalletService;

/**
 * Hello world!
 */
public class Gateway {
    public static void main(String[] args) {

        Address seedAddress = Address.create("172.28.29.65", 4802);





        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

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

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
