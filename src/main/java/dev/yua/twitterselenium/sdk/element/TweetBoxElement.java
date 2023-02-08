package dev.yua.twitterselenium.sdk.element;

import dev.yua.twitterselenium.sdk.entity.Tweet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class TweetBoxElement {
    public TwitterElement outer = new TwitterElement(By.className("DraftEditor-root"));
    public TwitterElement textInput = new TwitterElement(By.className("public-DraftEditor-content"));
    public TwitterElement mediaInput = new TwitterElement(By.xpath("//input[@type='file']"));
    public TwitterElement publishButton = new TwitterElement(By.cssSelector("div[data-testid='tweetButtonInline']"));


    public void tweet(WebDriver driver, Tweet tweet) throws FileNotFoundException {
        if(tweet.getFile() != null && !tweet.getFile().exists())
            throw new FileNotFoundException();

        this.outer.click(driver);
        if(tweet.getText() != null)
            this.textInput.sendKeys(driver, tweet.getText());

        try {
            Thread.sleep(1230);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(tweet.getFile() != null)
            this.mediaInput.sendKeys(driver, tweet.getFile().getAbsolutePath());

        this.publishButton.click(driver);
    }


}
