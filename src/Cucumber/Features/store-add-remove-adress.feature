Feature: Create new address


  Scenario Outline: User can add and remove new address
    Given an open browser with opened store site
    And logged user with credentials: "ProjektZaliczenie1@test.pl":"TrudneHaslo1"
    When User creating new address with input <alias> <address> <city> <zip/postal code> <country> <phone>
    Then User sees new address "Jako Tako" <alias> <address> <city> <zip/postal code> <country> <phone>
    And User deleting new added address
    And Correct address was deleted <alias> <address> <city> <zip/postal code> <country> <phone>
    And close browser
    Examples:
      | alias  | address    | city     | zip/postal code | country          | phone     |
      | "Jako" | "Dluga 33" | "Gdynia" | "80-180"        | "United Kingdom" | "1231231" |


