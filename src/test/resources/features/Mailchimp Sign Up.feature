Feature:

  Scenario: Correct registration
    Given user is on the Sign Up page
    Given the user has accepted cookies
    When  the required fields been correctly filled
    When  user clicks the Sign Up button
    Then  user will be registered

  Scenario: Username with more than 100 characters
    Given user is on the Sign Up page
    Given the user has accepted cookies
    When  user enter username with more than 100 characters
    When  user enters correct mail and password
    When  user clicks the Sign Up button
    Then  user will get an error message indicating username is too long

  Scenario: Username already taken
    Given user is on the Sign Up page
    Given the user has accepted cookies
    When  user enters username already taken
    When  user enters correct mail and password
    When  user clicks the Sign Up button
    Then  user will get an error message indicating username is taken

  Scenario: Email missing
    Given user is on the Sign Up page
    Given the user has accepted cookies
    When  user enters correct username and password
    When  user clicks the Sign Up button
    Then  user will get an error message indicating email is missing