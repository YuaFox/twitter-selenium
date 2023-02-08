package dev.yua.twitterselenium;

import dev.yua.twitterselenium.sdk.TwitterClient;
import dev.yua.twitterselenium.sdk.entity.Tweet;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        TwitterClient client = TwitterClient.createClient(false);
        try {
            client.sendTweet(new Tweet().setText("Tweet patrocinado por Selenium").setFile(new File("/home/yua/Documents/twitter-selenium/build/libs/fox.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}