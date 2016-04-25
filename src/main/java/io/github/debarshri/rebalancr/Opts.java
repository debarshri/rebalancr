package io.github.debarshri.rebalancr;

import com.lexicalscope.jewel.cli.Option;

public interface Opts {

    @Option(shortName="p",defaultValue="4567")
    Integer getPort();

    @Option(shortName="m",defaultValue="host.mapping")
    String getMapping();
}
