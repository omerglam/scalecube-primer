package gateway.main;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * Hello world!
 *
 */
public class Gateway
{
    public static void main( String[] args ) {

        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        router.route("/buy").handler(routingContext -> {

        });

        router.route("quote")

        vertx.createHttpServer().
    }
}
