package io.github.debarshri.rebalancr;

import com.lexicalscope.jewel.cli.CliFactory;
import spark.Spark;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static io.github.debarshri.rebalancr.MappingProcessor.process;
import static org.apache.commons.io.FileUtils.readLines;
import static spark.Spark.*;
import static spark.Spark.get;

public class RebalancrMain {
    public static void main(String[] args) throws IOException {
        final Opts opts = CliFactory.parseArguments(Opts.class, args);
        File file = new File(opts.getMapping());

        if(!file.exists())
        {
            file.createNewFile();
        }

        //Todo only get operations allowed
        Map<String, Mapping> params = process(readLines(file));

        Spark.port(opts.getPort());

        get("/", new Balancr(params));
        get("/*", new Balancr(params));
        post("/add", new AddMapping(params,file));
     //   post("/*", new BalancrPost(params));
    }
}
