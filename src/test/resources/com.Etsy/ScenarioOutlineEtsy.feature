Feature: scenario outline test

  Background: Etsy navigation
    Given the user goes the Etsy
    # Scenario Outline: and Scenario Template: are same
    # Examples and Scenario are the same
  @etsyOutline
  Scenario Outline: Etsy Search Validation with outline
    When the user search in etsy with "<searchValue>"
    Then the user validate title "<title>" and url "<etsyUrl>"
    Examples:
      | searchValue    | title                  | etsyUrl |
      | winter hat     | Winter hat \| Etsy     | winter |
      | hat            | Hat \| Etsy            | hat     |
      | winter soldier | Winter soldier \| Etsy | soldier |

    # to organize your code in Intellij you need to  use option+command+l

