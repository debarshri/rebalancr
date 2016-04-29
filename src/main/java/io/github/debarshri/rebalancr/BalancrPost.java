package io.github.debarshri.rebalancr;

import spark.Request;
import spark.Response;
import spark.Route;

import static org.apache.http.client.fluent.Request.Post;

public class BalancrPost implements Route {

    //todo add headers
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String[] split = request.host().split(":");

        String hostname = split[0];

        if (request.splat().length == 0) {
            Mapping mapping = MappingListener.params().get(hostname.trim());

            if (mapping == null) {
                System.out.println("Error link not found");
                return null;
            }

            String redirectedUrl = mapping.getRedirectedUrl();
            System.out.println(redirectedUrl);

            return Post(redirectedUrl).execute().returnContent();

        } else {

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

                return Post(redirectedUrl).execute().returnContent();
            }
        }
        return "Site not found";
    }
}
