Feature: Retrieve ID of Bitcoin, UsdTether and Ethereum and covert them to Bolivian Boliviano
  As an end user of the coinmarketcap api
  I should be able to retrieve the ID of few currencies
  And convert them to Bolivian Boliviano

  @api_task1 @apiTask
  Scenario: Retrieve few IDs and covert them to Bolivian Boliviano
    Given the user retrieve the IDs of the below currencies
      | BTC  |
      | USDT |
      | ETH  |
    Then the user convert them to Bolivian Boliviano currency BOB
