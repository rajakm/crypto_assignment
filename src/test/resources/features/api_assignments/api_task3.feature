Feature: Retrieve the Info of first 10 IDs and verify if all have mineable tag and correct cryptocurrency
  As an end user of the coinmarketcap api
  I should be able to retrieve the Info of first 10 IDs
  And verify if all the currencies have mineable tag
  And verify if all of them are having correct cryptocurrency

  @api_task3 @apiTask
  Scenario Outline: Retrieve the Info and verify the tag and cryptocurrency
    Given the user retrieve the info of Id <currencyID>
    Then verify the currency is having tag value as mineable
    And verify the currency with Id <currencyID> is having correct cryptocurrency <currencyName>
    Examples:
      | currencyID | currencyName |
      | 1          | Bitcoin      |
      | 2          | Litecoin     |
      | 3          | Namecoin     |
      | 4          | Terracoin    |
      | 5          | Peercoin     |
      | 6          | Novacoin     |
      | 7          | Devcoin      |
      | 8          | Feathercoin  |
      | 9          | Mincoin      |
      | 10         | Freicoin     |


