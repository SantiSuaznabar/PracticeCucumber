Feature: Login Sauce Demo
  Background: User login into Sauce Demo page
    Given Im in sauce demo web page

    @loginWithValidCredentials
    Scenario: Login into Sauce Demo page with valid credentials
      Given I set the user name field with "standard_user"
      And I set the password field with "secret_sauce"
      When I click on login button
      Then The home page should be displayed

    @loginInvalidCredentials
    Scenario: Login into Suce Demo page with invalid credentials
      Given I set the user name field with "wrong_user"
      And I set the password field with "wrong password"
      When I click on login button
      Then An error message that says "Epic sadface: Username and password do not match any user in this service" is displayed

    @Logout
    Scenario: Try Logout Option
      Given I login with "standard_user" and "secret_sauce"
      Then The home page should be displayed
      When I click the hamburger button
      And  Click the Logout Button
      Then I should be in the login page again

      @NumberInCart
      Scenario: Add an item then check if the number in the cart changed
        Given I login with "standard_user" and "secret_sauce"
        Then The home page should be displayed
        When I add a Sauce Labs Backpack
        Then The number in the cart should be: "1"
      @LowToHighFilter
      Scenario: See if the Price(Low to High) works
        Given I login with "standard_user" and "secret_sauce"
        Then The home page should be displayed
        When I select the low to high filter
        Then All items should be displayed sorted