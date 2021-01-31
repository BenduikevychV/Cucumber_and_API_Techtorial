Feature: Validation of New order using Scenario Outline

  Scenario Outline: New Order Validation
    Given the demoUser enters username "Tester"
    When the demoUser enters password "test"
    Then the user enter product info "<productName>" and "<quantity>"
    And the user enter address info "<name>", "<address>", "<city>", "<state>", "<zipCode>"
    * the user enter payment info "<cardType>", "<cardNum>", "<expDate>"
    Then the user validate success message
    And  the user validate new order details "<productName>", "<quantity>", "<name>", "<address>", "<city>", "<zipCode>", "<state>", "<cardType>", "<cardNum>", "<expDate>"
    Examples:
      | productName | quantity | name    | address      | city        | zipCode | state      | cardType         | cardNum          | expDate |
      | MyMoney     | 10       | David   | 2200 E devon | Des Plaines | 60028   | Illinois   | Visa             | 4444333322221111 | 05/24   |
      | FamilyAlbum | 5        | John    | 2200 E devon | Chicago     | 60058   | Arizona    | MasterCard       | 4444333322226666 | 05/26   |
      | ScreenSaver | 3        | Jessica | 2200 E river | Los Angeles | 40058   | California | American Express | 4444333322228888 | 08/22   |

