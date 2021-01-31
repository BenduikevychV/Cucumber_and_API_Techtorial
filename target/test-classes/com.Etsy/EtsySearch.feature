Feature: Validate search in Etsy

  Background: It will run before each scenario
    Given the user goes the Etsy

    # we need to run three scenario and
    # before every scenario I want to navigate the etsy except second scenario

  @etsy @tt
  Scenario: Validation of search in etsy 1
    When the user search in etsy with "winter hat"
    Then the user validate title "Winter hat | Etsy" and url "winter"


  @etsy
  Scenario: Validation of search in etsy 2
    When the user search in etsy with "winter soldier"
    Then the user validate title "Winter soldier | Etsy" and url "soldier"

  @etsy @tt
  Scenario: Validation of search in etsy 3
    When the user search in etsy with "hat"
    Then the user validate title "Hat | Etsy" and url "hat"