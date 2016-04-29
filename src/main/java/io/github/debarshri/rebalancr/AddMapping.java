package io.github.debarshri.rebalancr;

import org.apache.commons.io.FileUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddMapping implements Route {
    private Map<String, Mapping> params;
    private File file;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public AddMapping(Map<String, Mapping> params, File file) {
        this.params = params;
        this.file = file;
    }

    public Object handle(Request request, Response response) throws Exception {

        final String key = request.queryParams("in");
        final String value = request.queryParams("out");
        final String masked = request.queryParams("mask");

        params.put(key.trim(), new Mapping(value.trim(),Boolean.valueOf(masked), "v1"));
        executorService.submit(new FileRunner(file, key+" -> "+value+" -> "+Boolean.valueOf(masked)+" -> v1\n"));

        return "Ok";
    }

    private class FileRunner implements  Runnable {
        private File file;
        private String mapping;

        public FileRunner(File file, String mapping) {
            this.file = file;
            this.mapping = mapping;
        }

        public void run() {
            try {
                FileUtils.writeStringToFile(file, mapping, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
