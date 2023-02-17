
Feature: BUSINESS WEBAPPLICATION
  @tag2
  Scenario Outline: BUYING PRODUCTS
    Given user on HOME page
    When user select"<type>" and "<product>"
    Then user click on minimum price product
    And  user do Add to Cart

    Examples: 
      | type  | product |
      | Baby |     toys |
     
