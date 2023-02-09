package dev.yua.twitterselenium.sdk;

import dev.yua.twitterselenium.sdk.page.TwitterHomePage;
import dev.yua.twitterselenium.sdk.page.TwitterNotificationPage;
import dev.yua.twitterselenium.sdk.entity.Notification;
import dev.yua.twitterselenium.sdk.entity.Tweet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class TwitterClient {

    public static TwitterHomePage homePage = new TwitterHomePage();
    public static TwitterNotificationPage notificationPage = new TwitterNotificationPage();

    public static TwitterClient createClient(){
        return TwitterClient.createClient(true);
    }
    public static TwitterClient createClient(boolean headless){
        TwitterClient twitterClient = new TwitterClient(headless);
        twitterClient.load();
        return twitterClient;
    }

    private WebDriver driver;

    private TwitterClient(boolean headless){
        File[] files = new File(System.getProperty("user.home")+"/.mozilla/firefox").listFiles();
        File profileFile = Arrays.stream(files).filter((f) -> f.getName().contains("default-release")).findFirst().get();
        FirefoxProfile profile = new FirefoxProfile(profileFile);
        profile.setPreference("dom.webdriver.enabled", false);
        profile.setPreference("useAutomationExtension", false);
        FirefoxOptions firefoxOptions = new FirefoxOptions().setProfile(profile);
        if(headless)
            firefoxOptions.addArguments("-headless");
        this.driver = new FirefoxDriver(firefoxOptions);
    }

    private void load(){
        this.driver.get("https://twitter.com");
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.className("DraftEditor-root")));
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void sendTweet(Tweet tweet) throws FileNotFoundException {
        TwitterClient.homePage.tweet(this.driver, tweet);
    }

    public List<Notification> getNotifications(){
        return TwitterClient.notificationPage.getNotifications(driver, false);
    }

    public List<Notification> getNotifications(boolean older){
        return TwitterClient.notificationPage.getNotifications(driver, older);
    }

    public void resetNotifications(){
        TwitterClient.notificationPage.resetNotifications(driver);
    }
}