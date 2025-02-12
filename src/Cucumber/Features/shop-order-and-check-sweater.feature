Feature: Wgl inny feature

  Scenario Outline: User can add and remove new address
    Given Logged user <email> <password> on store site
    When User checks <discount> and order <amount> of <item> in <size>

    Examples:
      | email                        | password       | discount | amount | item                          | size |
      | "ProjektZaliczenie1@test.pl" | "TrudneHaslo1" | "20%"    | "4"       | "Hummingbird Printed Sweater" | "M"  |
