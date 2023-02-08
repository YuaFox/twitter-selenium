package dev.yua.twitterselenium.sdk.page;

import dev.yua.twitterselenium.sdk.element.TwitterElement;
import dev.yua.twitterselenium.sdk.entity.Notification;
import dev.yua.twitterselenium.sdk.entity.NotificationType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TwitterNotificationPage {

    public TwitterElement pageSelector = new TwitterElement(By.cssSelector("a[href='/notifications']"));
    public TwitterElement outer = new TwitterElement(By.cssSelector("div[aria-label='Timeline: Notifications']"));

    public List<Notification> getNotifications(WebDriver driver){
        if(!driver.getTitle().contains("Notifications /")) {
            pageSelector.click(driver);
        }
        WebElement element = outer.getElement(driver);
        List<WebElement> notificationsWeb = element.findElements(By.tagName("article"));
        List<Notification> notifications = new ArrayList<>();
        for(WebElement e : notificationsWeb){
            Notification notification = Notification.parseNotification(e.getText());
            notifications.add(notification);
        }
        return notifications;
    }

}
