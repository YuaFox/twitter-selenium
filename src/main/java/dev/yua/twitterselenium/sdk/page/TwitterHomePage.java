package dev.yua.twitterselenium.sdk.page;

import dev.yua.twitterselenium.sdk.element.TweetBoxElement;
import dev.yua.twitterselenium.sdk.element.TwitterElement;
import dev.yua.twitterselenium.sdk.entity.Tweet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class TwitterHomePage {

    public TwitterElement pageSelector = new TwitterElement(By.cssSelector("a[href='/home']"));
    public static TweetBoxElement tweetBox = new TweetBoxElement();

    public void tweet(WebDriver driver, Tweet tweet) throws FileNotFoundException {
        if(!driver.getTitle().contains("Home /")) {
            pageSelector.click(driver);
        }
        tweetBox.tweet(driver, tweet);
    }
}
