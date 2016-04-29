package io.github.debarshri.rebalancr;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static io.github.debarshri.rebalancr.MappingProcessor.process;
import static org.apache.commons.io.FileUtils.readLines;

public class MappingListener {

    private static Map<String, Mapping> params;

    public static void start(File file) {
        try {

            while (true)
            {
                params = process(readLines(file));
                Thread.sleep(30000);
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Mapping> params() {
        return params;
    }

}


