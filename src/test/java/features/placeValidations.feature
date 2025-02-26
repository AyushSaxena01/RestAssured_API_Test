Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if the place is being successfully added using AddPlaceAPTI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then The API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "getAddPlaceAPI"

    Examples:
    |name      |language  |address        |
    |BNS       |English   |E-3, 10no. stop|
    |Amado     |French    |E-2, 7no. stop |

    @DeletePlace
    Scenario: Verify if delete place functionality is working

      Given DeletePlace Payload
      When User calls "deletePlaceAPI" with "Post" http request
      Then The API call is successful with status code 200
      And "status" in response body is "OK"

