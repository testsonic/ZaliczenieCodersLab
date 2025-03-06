Feature: Wgl inny feature

  Scenario Outline: User check discount and order an item with pay by check option
    Given Logged user <email> <password> on store site
    When User checks <discount> of <item>
    And User order <amount> in <size>
    And 'Pay by check' with 'pick up in store'
    And Take a screenshot with order confirmation and price
    Then User go to order history check total price and status
    And Close browser

    Examples:
      | email                        | password       | discount | amount | item                          | size |
      | "ProjektZaliczenie1@test.pl" | "TrudneHaslo1" | "0%"    | "4"    | "The Best Is Yet To Come'..." | "L"  |
