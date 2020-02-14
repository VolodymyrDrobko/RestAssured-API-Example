Feature:
  Verify different POST operations using REST-assured

  Scenario: Verify post operation with body parameter
    Given I perform POST operation for "/posts/"
    Then I should see new author "/JavaMan/"

  Scenario: Verify post operation for Profile
    Given I perform POST operation for "/posts/{profileNo}/profile" with body
      | name | profile |
      | Sam  |    2    |
    Then I should see the body has name as "Sam"