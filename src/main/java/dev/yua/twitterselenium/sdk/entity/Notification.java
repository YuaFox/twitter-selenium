package dev.yua.twitterselenium.sdk.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Notification {

    public NotificationType type;
    public String handle;
    public String text;

    public WebElement element = null;

    public static By textSelector = By.cssSelector("div[data-testid='tweetText']");

    public static Notification parseNotification(WebElement element){
        String string = element.getText();
        Notification notification = new Notification();
        String[] lines = string.split("\\n");
        if(lines.length < 2) return notification;
        if(lines[1].startsWith("@")){
            notification.type = NotificationType.REPLY;
            notification.handle = lines[1];
            List<WebElement> elements = element.findElements(textSelector);
            if(!elements.isEmpty())
                notification.text = elements.get(0).getText();
        }
        return notification;
    }

    public Notification(){
        this.type = NotificationType.UNSUPPORTED;
    }


}
