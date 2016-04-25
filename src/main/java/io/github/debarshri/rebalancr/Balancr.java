package io.github.debarshri.rebalancr;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

import static org.apache.http.client.fluent.Request.*;

/**
 * curl -L "http://artifact.onlineeuropeancourse.eu/get/propr"
 */
public class Balancr implements Route {
    private Map<String, Mapping> params;

    public Balancr(Map<String, Mapping> params) {
        this.params = params;
    }

    //Todo masking..
    public Object handle(Request request, Response response) throws Exception {

        String[] split = request.host().split(":");

        String hostname = split[0];

        if (request.splat().length == 0) {
            Mapping mapping = params.get(hostname.trim());

            String redirectedUrl = mapping.getRedirectedUrl();
            if (mapping.isMasked()) {
                return Get(redirectedUrl);
            }

            response.status(302);
            response.redirect(redirectedUrl);
        }

        String s1 = request.splat()[0];
        String s = params.get(hostname.trim()).getRedirectedUrl();

        if (s != null) {
            if (s1 == null) {
                s1 = "";
            }

            System.out.println("Redirecting " + s + "/" + s1);
            response.status(302);
            response.redirect(s + "/" + s1);
        }

        return "Site not found";
    }
}
