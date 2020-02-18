Feature:
  Verify different POST operations using REST-assured

  Scenario: Verify post operation for Post
    Given I perform POST operation for "/posts/" with body
      | id | title  | author |
      | 5  | Python | Sam    |
    Then I should see the body has author as "Sam"