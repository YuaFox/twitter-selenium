package dev.yua.twitterselenium.sdk.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TwitterElement {

    private By selector;

    public TwitterElement(By selector){
        this.selector = selector;
    }

    public WebElement getElement(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    public TwitterElement click(WebDriver driver){
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(selector));
            Thread.sleep(new Random().nextInt(1, 2000));
            element.click();
            Thread.sleep(new Random().nextInt(1, 2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public TwitterElement sendKeys(WebDriver driver, CharSequence sequence){
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(selector));
            Thread.sleep(new Random().nextInt(250, 2000));
            element.sendKeys(sequence);
            Thread.sleep(new Random().nextInt(250, 2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

}
