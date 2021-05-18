Feature: User can set view and edit restriction on  a page


  Background: User create a new page

    Given user is on confluence login page
    And user logs in with username and password
    Then verify user is logged in
    And user navigates to confluence page
    Then verify user is on confluence page

  Scenario:  user sets restriction to Anyone on Confluence can view and edit  only for page ‘Test page’
    Given User is on test confluence page
    And user sets restriction to Anyone on confluence can view and edit
    # When user1 navigates to page ‘tconf’
    # Then they can view the page
    # And the edit button is enabled for the user
