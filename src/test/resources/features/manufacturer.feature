Feature: Everything related to Manufacturers

  Scenario: Getting all manufacturers
    When the user call get endpoint
    Then the user receives status code of 200

  Scenario: Adding a manufacturer
    Given I have a valid manufacturer object with id 1000004 and name "abc corporation" and affiliation "xyz alliance" and ceo "patrick jane"
    When I make a post request to manufacturer endpoint
    Then I should receive a status code of 201

