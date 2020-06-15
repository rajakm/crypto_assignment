package com.crypto.assignment.homepage;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageQuestions extends UIInteractionSteps {

    @Step
    public void verifyLoadMoreLink() {
        $(HomePage.LOAD_MORE_BUTTON).isDisplayed();
    }

    @Step
    public void verifyNumberOfRows(int expectedCount) {
        int actualCount = $(HomePage.CRYPTO_TABLE).findElements(By.tagName("tr")).size();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Step
    public void verifyWatchListTab(List<String> expectedCurrencies) {

        List<String> actualCurrencies = new ArrayList<>();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> rows = $(HomePage.CRYPTO_TABLE).findElements(By.tagName("tr"));

        //row iteration
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            actualCurrencies.add(cols.get(1).getText());
        }

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualCurrencies)
                .isEqualTo(expectedCurrencies);
        softly.assertAll();
    }

    @Step
    public void verifyPriceRange(List<String> filteredPriceDetails, Double fromPrice, Double toPrice) {
        List<Double> filteredPrices = new ArrayList<>();

        for (String filteredPriceDetail : filteredPriceDetails) {
            filteredPrices.add(Double.valueOf(filteredPriceDetail.substring(1)));
        }

        for (Double filteredPrice : filteredPrices) {
            assertThat(filteredPrice >= fromPrice && filteredPrice <= toPrice).isTrue();
        }
    }
}
