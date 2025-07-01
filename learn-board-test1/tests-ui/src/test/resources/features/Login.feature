Feature: User login
  Story link: https://jirapct.epam.com/jira/browse/EPMLSTRLB-166

  Scenario Outline: Verify user can login with valid credentials
    Given enter email "<email>" into Email field
    When click Submit button
    And enter password "<password>" into Password field
    And click Submit button
    Then Home page is opened
    And user is logged in
    When open My account menu
    Then user email is equal to "<email>"

    Examples:
      | email                                       | password                  |
      | auto_epm-lstr_learn_board_dev_test@epam.com | kuh)d5HkRpa_mi2XPWs9)]Sqi |

  @TC=EPMLSTRLB-1077
  Scenario Outline: Verify user can login with valid credentials and email in uppercase
    Given enter email "<email>" into Email field
    When click Submit button
    And enter password "<password>" into Password field
    And click Submit button
    Then Home page is opened
    And user is logged in
    When open My account menu
    Then user email is equal to "<email>"

    Examples:
      | email                                       | password                  |
      | AUTO_EPM-LSTR_LEARN_BOARD_DEV_TEST@EPAM.COM | kuh)d5HkRpa_mi2XPWs9)]Sqi |

  @TC=EPMLSTRLB-1079
  Scenario: Verify user can not login with invalid email
    When enter email "test_1@epam.com" into Email field
    And click Submit button
    Then email error message is displayed

  @TC=EPMLSTRLB-1079
  Scenario Outline: Verify user can not login with invalid password
    When enter email "<email>" into Email field
    And click Submit button
    And enter password "<password>" into Password field
    And click Submit button
    Then password error message is displayed

    Examples:
      | email                                       | password |
      | Auto_EPM-LSTR_Learn_Board_Dev_Test@epam.com | 12345    |

  @TC=EPMLSTRLB-1078
  Scenario: Verify user can not login with empty email
    Given Email field is empty
    When click Submit button
    Then email error message is displayed

  @TC=EPMLSTRLB-1078
  Scenario Outline: Verify user can not login with empty password
    Given enter email "<email>" into Email field
    When click Submit button
    And Password field is empty
    And click Submit button
    Then password error message is displayed

    Examples:
      | email                                       |
      | Auto_EPM-LSTR_Learn_Board_Dev_Test@epam.com |