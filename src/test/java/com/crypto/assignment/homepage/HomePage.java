package com.crypto.assignment.homepage;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("page:home.page")
public class HomePage extends PageObject {
  static private EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
  static final String VIEWALL_URL = EnvironmentSpecificConfiguration.from(env)
          .getProperty("viewall.page");

  static final String CRYPTO_TABLE = "//div[@class='cmc-table__table-wrapper-outer']//div//table//tbody";
  static final By LOAD_MORE_BUTTON = By.className("cmc-table-listing__loadmore");
  static final By WATCH_BUTTON = By.xpath("//*[@class='cmc-toolbar-button__trigger']//span[contains(text(), 'Watch')]");
  static final String WATCHLIST_URL = "https://coinmarketcap.com/watchlist/";
  static final By CRYPTO_DROP_DOWN = By.xpath("//div[@class='cmc-popover__trigger']//span[contains(text(),'Cryptocurrencies')]");
  static final By FULL_LIST_LINK = By.xpath("//li[@class='cmc-tab__trigger cmc-tab__trigger--selected']//li[3]//a[1]");
  static final By FILTER_LINK = By.xpath("//*[@class='cmc-table-listing__filter-button cmc-button cmc-button--color-default wn9odt-0 krkHSX']");
  static final By PRICE_BUTTON = By.xpath("//button[contains(text(),'Price')]");
  static final By PRICE_FROM = By.xpath("//input[@placeholder='0']");
  static final By PRICE_TO = By.xpath("//input[@placeholder='99,999']");
  static final By PRICE_APPLY = By.xpath("//button[contains(text(),'Apply')]");
}
