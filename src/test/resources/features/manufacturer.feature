Feature: Everything related to Manufacturers

  Scenario: Getting all manufacturers
    Given I have a valid token for role "user"
    When I call get manufacturers endpoint
    Then the user receives status code of 200

  Scenario: Adding a manufacturer
    Given I have a valid token for role "admin"
    And I have a valid manufacturer object with id 1000004 and name "abc corporation" and affiliation "xyz alliance" and ceo "patrick jane"
    When I make a post request to manufacturers endpoint
    Then I should receive a status code of 201




