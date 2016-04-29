package io.github.debarshri.rebalancr;

import spark.Request;
import spark.Response;
import spark.Route;

import static org.apache.http.client.fluent.Request.*;

/**
 * curl -L "http://artifact.onlineeuropeancourse.eu/get/propr"
 */
public class Balancr implements Route {



    public Object handle(Request request, Response response) throws Exception {

        String[] split = request.host().split(":");

        String hostname = split[0];

        if (request.splat().length == 0) {
            Mapping mapping = MappingListener.params().get(hostname.trim());

            if(mapping == null)
            {
                System.out.println("Error link not found");
                return null;
            }

            String redirectedUrl = mapping.getRedirectedUrl();
            if (mapping.isMasked()) {
                System.out.println(redirectedUrl);
                return Get(redirectedUrl).execute().returnContent();
            }

            response.status(302);
            response.redirect(redirectedUrl);
        }
        else {

            String s1 = request.splat()[0];
            Mapping mapping = MappingListener.params().get(hostname.trim());
            if (mapping == null) {
                System.out.println("Error link not found");
                return null;
            }

            String s = mapping.getRedirectedUrl();

            if (s != null) {
                if (s1 == null) {
                    s1 = "";
                }

                System.out.println("Redirecting " + s + "/" + s1);

                String redirectedUrl = s + "/" + s1;

                if (mapping.isMasked()) {
                    System.out.println(redirectedUrl);
                    return Get(redirectedUrl).execute().returnContent();
                }

                response.status(302);
                response.redirect(redirectedUrl);
            }
        }
        return "Site not found";
    }
}
