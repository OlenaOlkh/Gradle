Feature: Header functionality

  @loginAsAdmin
  Scenario: Verify header left group elements
    Given Home page is opened
    Then logo is displayed
    And Schedule tab is displayed
    And Settings tab is displayed
    And Reports tab is displayed

  @loginAsAdmin
  Scenario: Verify header right group elements
    Given Home page is opened
    Then Bell icon is displayed
    And New Event button is displayed
    And Dark Mode switcher is displayed
    And Info link is displayed
    And user avatar is displayed