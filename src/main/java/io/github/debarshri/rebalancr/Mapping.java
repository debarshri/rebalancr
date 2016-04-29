package io.github.debarshri.rebalancr;

public class Mapping {
    private String redirectedUrl;
    private boolean isMasked;
    private String version;

    public Mapping(String redirectedUrl, boolean isMasked, String version) {
        this.redirectedUrl = redirectedUrl;
        this.isMasked = isMasked;
        this.version = version;
    }

    public String getRedirectedUrl() {
        return redirectedUrl;
    }

    public boolean isMasked() {
        return isMasked;
    }

    public String getVersion() {
        return version;
    }

}
