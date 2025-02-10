Feature: Create new address


  Scenario Outline: User can add and remove new address
    Given an open browser with opened store site
    And logged user with credentials:<email>:<password>
    When User creating new address with input <alias> <address> <city> <zip/postal code> <country> <phone>
    Then User sees new address <nameAndSurname> <alias> <address> <city> <zip/postal code> <country> <phone>
    And User deleting new added address
    And Correct address was deleted <alias> <address> <city> <zip/postal code> <country> <phone>
    And close browser
    Examples:
      | email                          | password       | nameAndSurname    | alias   | address      | city     | zip/postal code | country          | phone             |
      | "ProjektZaliczenie1@test.pl"   | "TrudneHaslo1" | "Jako Tako"     | "Jako"  | "Dluga 33"   | "Gdynia" | "80-180"        | "United Kingdom" | "1231231"         |
      | "wprlfsndiprqmjxlrq@poplk.com" | "TrudneHaslo1" | "Karol Kowalski" | "Kowal" | "Szeroka 55" | "Gorzow"   | "01005"         | "United Kingdom" | "+22 311 4512 21" |
      | "wprlfsndiprqmjxlrq@test.com" | "Kolo123" | "Karol Kowalski" | "Kowal" | "Szeroka 55" | "Gorzow"   | "01005"         | "United Kingdom" | "+22 311 4512 21" |
