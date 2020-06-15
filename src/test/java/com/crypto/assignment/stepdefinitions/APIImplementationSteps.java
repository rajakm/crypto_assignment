package com.crypto.assignment.stepdefinitions;

import com.crypto.assignment.apiessentials.ApiActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;
import java.util.ArrayList;
import java.util.List;

public class APIImplementationSteps {

    @Steps
    ApiActions apiActions;

    private List<Long> currencyIDs = new ArrayList<>();

    @Given("the user retrieve the IDs of the below currencies")
    public void the_user_retrieve_the_IDs_of_below_currencies(List<String> currencies) {
        currencyIDs = apiActions.retrieveTheCurrencyIDs(currencies);
    }

    @Then("the user convert them to Bolivian Boliviano currency (.*)")
    public void the_user_convert_them_to_Bolivian_Boliviano(String convertCurrency) {
        apiActions.convertToBoliviano(currencyIDs, convertCurrency);
    }

    @Given("the user make get info call for Ethereum with id (.*)")
    public void the_user_make_an_info_call_for_Ethereum(long id) {
        apiActions.getInfoCallForCurrency(id);
    }

    @Then("verify the below details in the response")
    public void the_user_should_able_to_verify_the_below_details(DataTable dataTable) {

        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            apiActions.verifyInfoCallDetail(columns.get(0), columns.get(1));
        }
    }

    @Given("the user retrieve the info of Id (.*)")
    public void the_user_retrieve_the_info_of_Id(long id) {
        apiActions.getInfoCallForCurrency(id);
    }

    @Then("verify the currency is having (.*) value as (.*)")
    public void verify_the_currency_with_Id_is_having_mineable_tag(String key, String value) {
        apiActions.verifyInfoCallDetail(key, value);
    }

    @Then("verify the currency with Id (.*) is having correct cryptocurrency (.*)")
    public void verify_the_currency_with_Id_is_having_correct_cryptocurrency(long id, String expectedCurrencyName) {
        apiActions.verifyCryptoCurrencyValueInInfoCall(id, expectedCurrencyName);
    }

}
