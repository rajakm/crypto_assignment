package com.crypto.assignment.stepdefinitions;

import com.crypto.assignment.homepage.HomePage;
import com.crypto.assignment.homepage.HomePageActions;
import com.crypto.assignment.homepage.HomePageQuestions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

public class UIImplementationSteps {

    @Steps
    HomePage homePage;

    @Steps
    HomePageActions homePageActions;

    @Steps
    HomePageQuestions homePageQuestions;

    List<String> allPriceDetails = new ArrayList<>();

    @Given("the user navigate to home page")
    public void the_user_navigate_to_home_page() {
        homePage.open();
    }

    @Given("verify the page displays (.*) records by default")
    public void verify_the_page_displays_records_by_default(Integer value) {
        homePageQuestions.verifyNumberOfRows(value);
    }

    @When("the user click View All button")
    public void the_user_click_View_All_button() {
        homePageActions.navigateToViewAll();
    }

    @Then("verify that the page should display (.*) records")
    public void verify_that_the_page_should_display_records(Integer value) {
        homePageQuestions.verifyNumberOfRows(value);
    }

    @Then("the page should have Load More button displays")
    public void the_page_should_have_Load_More_button_displays() {
        homePageQuestions.verifyLoadMoreLink();
    }

    @When("the user likes to add below currencies to watch list")
    public void the_user_likes_to_add_below_currencies_to_watch_list(DataTable dt) {
        List<String> currencies = dt.asList(String.class);

        for (String currency : currencies) {
            homePageActions.addToWatchList(currency);
        }
    }

    @Then("the user navigates to watchlist page")
    public void the_user_navigates_to_watchlist_page() {
        homePageActions.clickWatchListTab();
    }

    @Then("verify all the added currencies are available in the watchlist page")
    public void verify_all_the_above_currencies_are_available_in_the_watchlist_page(DataTable dt) {
        List<String> currencies = dt.asList(String.class);
        homePageQuestions.verifyWatchListTab(currencies);
    }

    @Given("the user select Full list from Crypto currencies dropdown")
    public void the_user_select_Full_list_from_Crypto_currencies_dropdown() {
        homePageActions.clickCryptoDropDown();
        homePageActions.clickFullListFromCrypto();
    }

    @Given("record the number of records in the page")
    public void record_the_number_of_records_in_the_page() {
        allPriceDetails = homePageActions.recordPriceDetails();
    }

    @When("the user filter the currencies by price between (.*) and (.*)")
    public void the_user_filter_the_currencies_by_price_between_and(String value1, String value2) {
        homePageActions.clickFilterButton();
        homePageActions.enterPriceValues(value1, value2);
        homePageActions.clickApplyButton();
    }

    @Then("verify that the page should display the currencies with the price between (.*) and (.*)")
    public void verify_that_the_page_should_display_the_currencies_with_the_price_between_and(Double value1, Double value2) {
        List<String> filteredPriceDetails = homePageActions.recordPriceDetails();
        homePageQuestions.verifyPriceRange(filteredPriceDetails, value1, value2);
    }

}
