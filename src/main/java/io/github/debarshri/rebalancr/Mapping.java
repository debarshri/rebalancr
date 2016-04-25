package io.github.debarshri.rebalancr;

public class Mapping {
    private String redirectedUrl;
    private boolean isMasked;

    public Mapping(String redirectedUrl, boolean isMasked) {
        this.redirectedUrl = redirectedUrl;
        this.isMasked = isMasked;
    }

    public String getRedirectedUrl() {
        return redirectedUrl;
    }

    public boolean isMasked() {
        return isMasked;
    }
}
