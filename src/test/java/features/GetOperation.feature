Feature:
  Verify different GET operations using REST-assured

  Scenario: Verify one title of the post
    Given I perform GET operation for "/posts"
    Then I should see the title as "Appium"

  Scenario: Verify all titles of the post
    Given I perform GET operation for "/posts/"
    Then I should see all posts titles

  Scenario: Verify author of the first post using JSON validation
    Given I perform GET operation for "/posts/" with query parameter
      | id |
      | 1  |
    Then I should see author name as "Taras" with JSON validation