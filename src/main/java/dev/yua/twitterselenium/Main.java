package dev.yua.twitterselenium;

import dev.yua.twitterselenium.sdk.TwitterClient;
import dev.yua.twitterselenium.sdk.entity.Notification;
import dev.yua.twitterselenium.sdk.entity.NotificationType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TwitterClient client = TwitterClient.createClient(false);

        List<Notification> notifications = client.getNotifications();
        for(Notification notification : notifications){
            if(notification.type == NotificationType.REPLY) System.out.println(notification.text);
        }

        notifications = client.getNotifications(true);
        for(Notification notification : notifications){
            if(notification.type == NotificationType.REPLY) System.out.println(notification.text);
        }

        /*
        try {
            client.sendTweet(new Tweet().setText("Tweet patrocinado por Selenium").setFile(new File("/home/yua/Documents/twitter-selenium/build/libs/fox.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }
}