Feature: Create and remove address

  Scenario Outline: User can add and remove new address
    Given an open browser with opened store site
    And logged user with credentials:<email>:<password>
    When User creating new address with input <alias> <address> <city> <zip/postal code> <country> <phone>
    Then User sees new address <nameAndSurname> <alias> <address> <city> <zip/postal code> <country> <phone>
    And User deleting new added address
    And Correct address was deleted <alias> <address> <city> <zip/postal code> <country> <phone>
    And close browser
    Examples:
      | email                        | password       | nameAndSurname | alias  | address      | city     | zip/postal code | country          | phone            |
      | "ProjektZaliczenie1@test.pl" | "TrudneHaslo1" | "Jako Tako"    | "Jako" | "Dluga 33"   | "Gdynia" | "80-180"        | "United Kingdom" | "1231231"        |
      | "pawlak7@test.net"           | "Trudnehaslo1" | "Jan Pawlak"   | "DAMA" | "Idealna 22" | "Tarnów" | "01005"         | "United Kingdom" | "+49 2133123124" |