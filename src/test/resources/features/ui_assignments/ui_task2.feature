Feature: Add few crypto currencies to watchlist and verify
  As an end user of the coinmarketcap website
  I should select few currencies to add to watchlist
  And confirm the currencies are added to watchlist

  @ui_task2 @uiTask
  Scenario: User select few crypto currencies and add to watchlist and verify
    Given the user navigate to home page
    When the user likes to add below currencies to watch list
      |Bitcoin   |
      |Ethereum  |
      |XRP       |
      |Litecoin  |
      |Monero    |
      |Dogecoin  |
    Then the user navigates to watchlist page
    And verify all the added currencies are available in the watchlist page
      |Bitcoin   |
      |Ethereum  |
      |XRP       |
      |Litecoin  |
      |Monero    |
      |Dogecoin  |



