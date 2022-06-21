Feature: Everything related to ships

  Scenario: Getting all ships
    Given I have a valid token for role "user" for ships
    When I call get ships endpoint
    Then I receive http status code of 200

  Scenario: Adding ships
    Given I have a valid token for role "admin" for ships
    And I have a valid ship object with name "Seafire" and manufacturerName "Rebel Alliance" and type "fighter" and length 190.00 and cost 2900000
    When I make a post request to ships
    Then I receive http status code of 201


