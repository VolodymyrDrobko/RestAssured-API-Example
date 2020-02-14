Feature:
  Verify different DELETE operations using REST-assured

  Scenario: Verify DELETE operation after POST
#    Given I perform POST operation for "/posts" with body as
#      | id | title       | author |
#      | 5  | API Testing | Ivan   |
    And I perform DELETE operation for "posts/{postId}/"
      | postId |
      | 5      |
#    And I perform GET operation with path "posts/{postId}/"
#      | postId |
#      | 5      |
#    Then I should not see the body with title as "API Testing"