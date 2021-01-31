Feature: Deserialize swagger pet

  Scenario Outline: get pet
    Given accept header is set to "ContentType.JSON"
    When the user execute "GET" request
    Then the status code is 200
    And  contentType header is "application/json"
    And  user verified <id> "<name>" <tags> size
    Examples:
      | id | name   | tags |
      | 5  | doggie | 1    |
