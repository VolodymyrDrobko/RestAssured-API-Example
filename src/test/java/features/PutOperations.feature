Feature:
  Verify different PUT operations using REST-assured

  Scenario: Verify put operation for Profile
    Given I perform POST operation for "/posts" with body as
      | id | title       | author |
      | 6  | API Testing | Ivan   |
    And I perform PUT operation for "/posts/{postId}"
      | id | title       | author |
      | 6  | API Testing | Oleg   |
    And I perform GET operation with "/posts/{postId}"
      | postId |
      | 6      |
    Then I "should not" see the body with author as "Ivan"