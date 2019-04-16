package com.epam.kinorating.command;

public class CommandResult {
    private final String url;
    private final boolean forward;

    public CommandResult(String url, boolean forward) {
        this.url = url;
        this.forward = forward;
    }

    public String getUrl() {
        return url;
    }

    public boolean isForward() {
        return forward;
    }

}
