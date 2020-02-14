Feature:
    Verify different GET operations using REST-assured

    Scenario: Verify one title of the post
        Given I perform GET operation for "/posts"
        Then I should see the title as "Appium"

    Scenario: Verify all titles of the post
        Given I perform GET operation for "/posts/"
        Then I should see all posts titles