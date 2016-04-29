package io.github.debarshri.rebalancr;

import com.lexicalscope.jewel.cli.CliFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        final Cli cli = CliFactory.parseArguments(Cli.class, args);
        FileUtils.writeStringToFile(new File(cli.getConfig()),
                cli.getIn().trim()+" -> "+cli.getOut().trim()+" -> "
                        +cli.getMasked()+" -> "+cli.getVersion()+"\n",
                true);
    }
}
