package com.crypto.assignment.homepage;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HomePageActions extends UIInteractionSteps {


    @Step
    public void navigateToViewAll() {
        getDriver().get(HomePage.VIEWALL_URL);
    }


    @Step
    public void addToWatchList(String currency) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        List<WebElement> rows = $(HomePage.CRYPTO_TABLE).findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                if (col.getText().equals(currency)) {
                    WebElement element = $(HomePage.CRYPTO_TABLE).findElement(By.linkText(currency));
                    js.executeScript("arguments[0].click();", element);

                    waitForCondition().until(
                            ExpectedConditions.visibilityOfElementLocated(HomePage.WATCH_BUTTON))
                            .click();
                    getDriver().navigate().back();
                    return;
                }
            }
        }
    }

    @Step
    public void clickWatchListTab() {

        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        getDriver().get(HomePage.WATCHLIST_URL);
    }

    @Step
    public void clickCryptoDropDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement element = $(HomePage.CRYPTO_DROP_DOWN);
        js.executeScript("arguments[0].click();", element);
    }

    @Step
    public void clickFullListFromCrypto() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement element = $(HomePage.FULL_LIST_LINK);
        js.executeScript("arguments[0].click();", element);
    }

    @Step
    public List<String> recordPriceDetails() {
        List<String> priceDetails = new ArrayList<>();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> rows = $(HomePage.CRYPTO_TABLE).findElements(By.tagName("tr"));

        //row iteration
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            priceDetails.add(cols.get(4).getText());
        }
        return priceDetails;
    }

    @Step
    public void clickFilterButton() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement element = $(HomePage.FILTER_LINK);
        js.executeScript("arguments[0].click();", element);
    }

    @Step
    public void enterPriceValues(String fromValue, String toValue) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement priceElement = $(HomePage.PRICE_BUTTON);
        js.executeScript("arguments[0].click();", priceElement);

        $(HomePage.PRICE_FROM).sendKeys(fromValue);
        $(HomePage.PRICE_TO).sendKeys(toValue);
    }

    @Step
    public void clickApplyButton() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement element = $(HomePage.PRICE_APPLY);
        js.executeScript("arguments[0].click();", element);
    }

}
