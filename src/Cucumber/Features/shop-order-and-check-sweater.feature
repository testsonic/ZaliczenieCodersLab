Feature: Wgl inny feature

  Scenario Outline: User can add and remove new address
    Given Logged user <email> <password> on store site
    When User checks <discount> and order <amount> of <item> in <size>
    And 'Pay by check' with 'pick up in store'
    Then Take a screnshoot with order confirmation and price
    And Close browser

    Examples:
      | email                        | password       | discount | amount | item                          | size |
      | "ProjektZaliczenie1@test.pl" | "TrudneHaslo1" | "20%"    | "4"    | "Hummingbird Printed Sweater" | "L"  |
