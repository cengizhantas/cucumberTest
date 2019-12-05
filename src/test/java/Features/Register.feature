# Created by cengizhan.tas at 03/12/2019
Feature: Register to the site
@register
  Scenario: User registers by successfully completing the registration form
    Given User navigates to digiturk website
    And User clicks on the subscribe button
    And Choose Monthly Pass with One Week Free Trial package
    And Validate package price
    And User enters a valid username
    And User enters a valid lastname
    And User enters a valid email
    And User enters a valid password
    When User clicks on the register button
    Then E-mail confirmation alert appears
    And Accept terms and conditions checkbox
    And Click the PAY NOW button
    And Fill the Cardholder's name
    And Fill the card number
    And Fill expire month
    And Fill expire year
    And Fill card verification code
    When Click confirm my payment
    Then Check error message

