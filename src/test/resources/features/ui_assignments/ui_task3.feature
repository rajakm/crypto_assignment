Feature: Compare the results after enable the filters
  As an end user of the coinmarketcap website
  I should be able to click view full list options
  And I should be able to filter the price of the currencies
  And verify the filter is working properly

  @ui_task3 @uiTask
  Scenario: User click Full list and then Filter the currencies based on Price and compare the results
    Given the user navigate to home page
    And the user select Full list from Crypto currencies dropdown
    And record the number of records in the page
    When the user filter the currencies by price between 100 and 300
    Then verify that the page should display the currencies with the price between 100 and 300
