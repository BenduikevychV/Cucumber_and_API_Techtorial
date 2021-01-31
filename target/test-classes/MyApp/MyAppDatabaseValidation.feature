@regressiontest @db @ui @smoketest
Feature: MyApp Database Validation

  Scenario: Validation UI data with DB data
    # First step I create a customer -> UI, API, POST, Database
    # Given User creates customer in UI/API/DB
    Given User go to MyApp home page
    When User gets the data from UI
    Then User validates UI data with DB
    # Then User deletes the customer ,UI, API, Delete, Database Delete