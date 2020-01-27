Feature:
    Verify different GET operations using REST-assured

    Scenario: Verify one title of the post
        Given I perform GET operation for "/post"
        When I perform GET for the post number "2"
        Then I should see the title as "Appium"

    Scenario: Verify titles order in the posts
        Given I perform GET operation for "/post"
        Then I should see the author names

    Scenario: Verify Path Parameter of Get
        Given I perform GET operation for "/post"
        Then I should verify path GET parameter

    Scenario: Verify Query Parameter of Get
        Given I perform GET operation for "/post"
        Then I should verify query GET parameter