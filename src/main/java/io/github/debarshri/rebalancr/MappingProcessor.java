package io.github.debarshri.rebalancr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MappingProcessor {
    public static Map<String, Mapping> process(List<String> s) {

        Map<String, Mapping> stringStringHashMap = new HashMap<>();
        s.stream().forEach(new Mapper(stringStringHashMap));

        return stringStringHashMap;
    }

    private static class Mapper implements Consumer<String> {
        private Map<String, Mapping> stringStringHashMap;

        public Mapper(Map<String, Mapping> stringStringHashMap) {
            this.stringStringHashMap = stringStringHashMap;
        }

        public void accept(String s) {
            String[] key = s.split("->");
            stringStringHashMap.put(key[0].trim(),
                    new Mapping(key[1].trim(),
                            Boolean.valueOf(key[2].trim()),"v1" ));
        }
    }
}
