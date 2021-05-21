Feature: User can set view and edit restriction on  a page


  Scenario Outline:  user sets restriction to Anyone on Confluence can view and edit  only for page ‘Test page’
    Given User log in to confluence with username "<bob_username>" and password "<bob_password>"
    Then verify user is logged in as username "<bob_display_name>"
    Given User "<bob_username>" navigates to test confluence page
    And User "<bob_username>" sets restriction to "Anyone on confluence can view and edit" for the test confluence page
    And User "<bob_username>" logs out
    When User log in to confluence with username "<sam_username>" and password "<sam_password>"
    Then verify user is logged in as username "<sam_display_name>"
    Then User "<sam_username>" navigates to test confluence page
    Then verify user "<sam_username>" " can see" edit button


  Examples:
    | bob_username | bob_display_name |bob_password | sam_username | sam_password | sam_display_name |
    | blaze.meter@yahoo.com| BLAZE METER |admin123 | mmtest319@gmail.com | admin123 | MMTEST319  |


  Scenario Outline:  user sets restriction to Only specific people can view and edit  for page ‘Test page’
    Given User log in to confluence with username "<bob_username>" and password "<bob_password>"
    Then verify user is logged in as username "<bob_display_name>"
    Given User "<bob_username>" navigates to test confluence page
    And User "<bob_username>" sets restriction to "Only specific people can view and edit" for the test confluence page
    And User "<bob_username>" sets "can edit" permission to user "<sam_display_name>"
    And User "<bob_username>" logs out
    When User log in to confluence with username "<sam_username>" and password "<sam_password>"
    Then verify user is logged in as username "<sam_display_name>"
    Then User "<sam_username>" navigates to test confluence page
    Then verify user "<sam_username>" "can see" edit button
    Then User "<sam_username>" logs out
    When User log in to confluence with username "<tom_username>" and password "<tom_password>"
    Then verify user is logged in as username "<tom_display_name>"
    When User tries to navigate to test confluence page
    Then User sees "<restricted_text>" text


    Examples:
      | bob_username | bob_display_name |bob_password | sam_username | sam_password | sam_display_name | tom_username | tom_display_name | tom_password | restricted_text |
      | blaze.meter@yahoo.com| BLAZE METER |admin123 | mmtest319@gmail.com | admin123 | MMTEST319  | sanjushatanishq@yahoo.co.in | TANISHQ SANJU | admin123| You've stumbled on restricted content |

  Scenario Outline:  user sets restriction to Anyone on Confluence can view, some can edit  for page ‘Test page’
    Given User log in to confluence with username "<bob_username>" and password "<bob_password>"
    Then verify user is logged in as username "<bob_display_name>"
    Given User "<bob_username>" navigates to test confluence page
    And User "<bob_username>" sets restriction to "Anyone on Confluence can view, some can edit" for the test confluence page
    And User "<bob_username>" sets "can edit" permission to user "<sam_display_name>"
    And User "<bob_username>" logs out
    When User log in to confluence with username "<sam_username>" and password "<sam_password>"
    Then verify user is logged in as username "<sam_display_name>"
    Then User "<sam_username>" navigates to test confluence page
    Then verify user "<sam_username>" "can see" edit button
    Then User "<sam_username>" logs out
    When User log in to confluence with username "<tom_username>" and password "<tom_password>"
    Then verify user is logged in as username "<tom_display_name>"
    Then User "<tom_username>" navigates to test confluence page
    Then verify user "<tom_username>" "cannot see" edit button


    Examples:
      | bob_username | bob_display_name |bob_password | sam_username | sam_password | sam_display_name | tom_username | tom_display_name | tom_password |
      | blaze.meter@yahoo.com| BLAZE METER |admin123 | mmtest319@gmail.com | admin123 | MMTEST319  | sanjushatanishq@yahoo.co.in | TANISHQ SANJU | admin123|