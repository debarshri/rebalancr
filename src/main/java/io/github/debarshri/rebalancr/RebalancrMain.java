package io.github.debarshri.rebalancr;

import com.lexicalscope.jewel.cli.CliFactory;
import spark.Spark;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.github.debarshri.rebalancr.MappingListener.*;
import static spark.Spark.*;
import static spark.Spark.get;

public class RebalancrMain {

    private static ExecutorService executorService =
            Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException {
        final Opts opts = CliFactory.parseArguments(Opts.class, args);
        File file = new File(opts.getMapping());

        if (!file.exists()) {
            file.createNewFile();
        }

        executorService.submit((Runnable) () -> start(file));

        Spark.port(opts.getPort());

        get("/", new Balancr());
        get("/*", new Balancr());
        post("/*", new BalancrPost());
        post("/", new BalancrPost());
    }
}
