package io.github.debarshri.rebalancr;

import com.lexicalscope.jewel.cli.Option;

public interface Cli {

    @Option(shortName="m",defaultValue="true")
    boolean getMasked();

    @Option(shortName="c",defaultValue="host.mapping")
    String getConfig();

    @Option(shortName="i")
    String getIn();

    @Option(shortName="o")
    String getOut();

    @Option(shortName="v", defaultValue = "v1")
    String getVersion();
}
