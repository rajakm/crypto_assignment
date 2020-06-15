package com.crypto.assignment.apiessentials;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.hamcrest.core.IsNull;

import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiActions {
    private Response response;
    private EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();

    private String BASE_URL = EnvironmentSpecificConfiguration.from(env)
            .getProperty("my.api.endpoint");
    private String API_KEY = EnvironmentSpecificConfiguration.from(env)
            .getProperty("my.api.key");

    //This method is to retrieve the currency id from the get Map call response
    @Step
    public List<Long> retrieveTheCurrencyIDs(List<String> currencies) {
        List<Long> currencyID = new ArrayList<>();
        getMapCall();
        List<ApiCurrencyPojo> currencyPojos = response.jsonPath().getList("data", ApiCurrencyPojo.class);

        currencyPojos.forEach(e -> {
            if(currencies.contains(e.getSymbol())){
                currencyID.add(e.getId());
            }
        });
        return currencyID;
    }

    //This method is to convert all the currency IDs to another currency
    @Step
    public void convertToBoliviano(List<Long> currencyIDs, String convertCurrency){
        currencyIDs.forEach(id -> {
            getCurrencyConvertCall(id, convertCurrency);
        });

    }

    //This method is to get info call for a particular currency
    @Step
    public void getInfoCallForCurrency(long id){
        String url = BASE_URL + "/cryptocurrency/info";
        response = SerenityRest
                .given().header("X-CMC_PRO_API_KEY", API_KEY)
                .queryParam("id", id)
                .header("Accept", "application/json")
                .when()
                .get(url);
        response.then()
                .statusCode(200);
    }

    //This method is to verify all the values in the get info call
    @Step
    public void verifyInfoCallDetail(String key, String expectedValue){
        String jsonPathCreatorLogoPath = "data.1027.logo";
        String jsonPathCreatorTechnicalDocPath = "data.1027.urls.technical_doc[0]";
        String jsonPathCreatorSymbolPath = "data.1027.symbol";
        String jsonPathCreatorDateAddedPath = "data.1027.date_added";
        String jsonPathCreatorPlatformPath = "data.1027.platform";
        String jsonPathCreatorTagsPath = "data.1027.tags[0]";

        String actualValue = null;
        JsonPath jsonPath = new JsonPath(response.body().asString());
        switch(key){
            case "logo":{
                actualValue = jsonPath.get(jsonPathCreatorLogoPath);
                break;
            }
            case "technical_doc":{
                actualValue = jsonPath.get(jsonPathCreatorTechnicalDocPath);
                break;
            }
            case "symbol":{
                actualValue = jsonPath.get(jsonPathCreatorSymbolPath);
                break;
            }
            case "date_added":{
                actualValue = jsonPath.get(jsonPathCreatorDateAddedPath);
                break;
            }
            case "platform":{
                actualValue = jsonPath.get(jsonPathCreatorPlatformPath);
                assertThat(actualValue, is(IsNull.nullValue()));
                break;
            }
            case "tags":{
                actualValue = jsonPath.get(jsonPathCreatorTagsPath);
                break;
            }
            default:{
                response.then().statusCode(200);
            }
        }
        if(actualValue != null)
        assertThat(actualValue, equalTo(expectedValue));
    }

    //This method is to verify the cryptocurrency name is intact with the name in the get map call
    @Step
    public void verifyCryptoCurrencyValueInInfoCall(long id, String expectedCurrencyName){
        String actualCurrencyName = fetchCurrencyNameFromInfoCall(id);
        assertThat(actualCurrencyName, equalTo(expectedCurrencyName));
    }

    //This private method is to make a get map call
    private void getMapCall(){
        String url = BASE_URL + "/cryptocurrency/map";
        response = SerenityRest
                .given().header("X-CMC_PRO_API_KEY", API_KEY)
                .header("Accept", "application/json")
                .when()
                .get(url);
    }

    //This private method is to make a get currency convert call
    private void getCurrencyConvertCall(long id, String convertCurrency){
        String url = BASE_URL + "/tools/price-conversion";
        response = SerenityRest
                .given().header("X-CMC_PRO_API_KEY", API_KEY)
                .header("Accept", "application/json")
                .queryParam("amount", 1)
                .queryParam("id", id)
                .queryParam("convert", convertCurrency)
                .when()
                .get(url);
        response.then()
                .statusCode(200);
    }

    //This private method is to retrieve the currency name from the get info call
    private String fetchCurrencyNameFromInfoCall(long id){
        String jsonPathCreatorNamePath = "data."+id+".name";
        JsonPath jsonPath = new JsonPath(response.body().asString());
        return jsonPath.get(jsonPathCreatorNamePath);
    }
}
