package dev.yua.twitterselenium.sdk.entity;

import java.io.File;

public class Tweet {

    private String text;
    private File file;

    public Tweet setText(String text) {
        this.text = text;
        return this;
    }

    public Tweet setFile(File file) {
        this.file = file;
        return this;
    }

    public File getFile() {
        return file;
    }

    public String getText() {
        return text;
    }


}
