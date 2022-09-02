@login
Feature: Login
  
  In order to to maintain health record of patient
  As a User
  I want to access OpenEMR application

  @valid
  Scenario Outline: valid Credentials
    Given I have browser with OpenEMR application
    When I Enter username as '<username>'
    And I Enter password as '<password>'
    And I select Language as '<language>'
    And I click on login
    Then I should get access to Portal with title 'OpenEMR'

    Examples: 
      | username  | password  | language         |
      | admin     | pass      | English (Indian) |
      | physician | physician | English (Indian) |

  Scenario: Invalid Credentials
    Given I have browser with OpenEMR application
    And I Enter username as 'admin'
    And I Enter password as 'john'
    And I Enter password as 'pass1'
    And I select Language as 'English (Indian)'
    And I click on login
    Then I should not get access to Portal with Error message as  'Invalid username or password'
