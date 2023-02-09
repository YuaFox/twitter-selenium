package dev.yua.twitterselenium.sdk.page;

import dev.yua.twitterselenium.sdk.element.TwitterElement;
import dev.yua.twitterselenium.sdk.entity.Notification;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwitterNotificationPage {

    public TwitterElement pageSelector = new TwitterElement(By.cssSelector("a[href='/notifications']"));
    public TwitterElement outer = new TwitterElement(By.cssSelector("div[aria-label='Timeline: Notifications']"));

    public void resetNotifications(WebDriver driver) {
        pageSelector.click(driver);
    }

    public List<Notification> getNotifications(WebDriver driver, boolean older){
        if(!driver.getTitle().contains("Notifications /")) {
            pageSelector.click(driver);
        }

        if(older) {
            List<Notification> start = this.getNotifications(driver, false);
            List<Notification> current;
            int tries = 0;
            Notification last = start.get(start.size()-1);
            while(tries < 6) {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("window.scrollBy(0,1200)");
                try {
                    Thread.sleep(new Random().nextInt(250, 2000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                current = this.getNotifications(driver, false);
                for(int i = 0; i < 5; i++){
                    if(current.get(i).element.equals(last)) {
                        while(i-->0){
                            current.remove(0);
                        }
                        return current;
                    }
                }
                tries++;
            }
        }

        WebElement element = outer.getElement(driver);
        List<WebElement> notificationsWeb = element.findElements(By.tagName("article"));
        List<Notification> notifications = new ArrayList<>();
        for(WebElement e : notificationsWeb){
            Notification notification = Notification.parseNotification(e);
            notification.element = e;
            notifications.add(notification);
        }
        return notifications;
    }

}
