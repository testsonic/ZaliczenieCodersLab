Feature: Create new address


  Scenario Outline: user can search any keyword
    Given an open browser with opened store site and login user with credentials: "ProjektZaliczenie1@test.pl":"TrudneHaslo1"
    When User creating new address with input <alias> <address> <city> <zip/postal code> <country> <phone>
    Then User sees new address
    And close browser
    Examples:
      | alias | address  | city   | zip/postal code | country | phone   |
      | "Jako"  | "Dluga 33" | "Gdynia" | "80-180"          | "United Kingdom"  | "1231231" |


