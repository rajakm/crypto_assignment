Feature: View all the results on Coin Market Cap home page
  As an end user of the coinmarketcap website
  I should be able to click view all button
  And confirm the results are displaying appropriately

  @ui_task1 @uiTask
  Scenario: User click ViewAll button to see 100 more results
    Given the user navigate to home page
    And verify the page displays 100 records by default
    When the user click View All button
    Then verify that the page should display 200 records
    And the page should have Load More button displays

