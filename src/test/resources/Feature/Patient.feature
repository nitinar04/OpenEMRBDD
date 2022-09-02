@patient
Feature: 
  In order to add patient records 
  As an admin
  I want to add, delete, edit patient records

  @addpatient
  Scenario Outline: Add Patient
    Given I have browser with OpenEMR application
    When I Enter username as '<username>'
    And I Enter password as '<password>'
    And I select Language as '<language>'
    And I click on login
    And I click on Patient Menu
    And I click on New Search menu
    And I fill the who section form
      | firstname   | lastname   | DOB    | gender   |
      | <firstname> | <lastname> | < DOB> | <gender> |
    And I click on create new patient
    And I click on confirm create new patient
    And I store the alert text and handle it
    And I close the happy birthday if available
    Then I should verify the alert text contains '<alerttext>'
    And I should get the added patient details as '<expected_text>'

    Examples: 
      | username | password | language         | firstname | lastname | DOB        | gender | alerttext  | expected_text                        |
      | admin    | pass     | English (Indian) | Jim1     | Wick     | 2015-08-30 | Male   | Assessment | Medical Record Dashboard - Jim1 Wick |
      | admin    | pass     | English (Indian) | Tim1     | Wick     | 2011-09-02 | Male   | Assessment | Medical Record Dashboard - Tim1 Wick   |
