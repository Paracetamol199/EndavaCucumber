Feature: Weather API

  @Smoke
  Scenario Outline: Check that the Weather API responds with 200 when using a valid city name and the data returned is correct
    Given A request is performed to the "weather" endpoint
    And Using as city name "<city>"
    When The GET http request is performed
    Then The response status code is "OK"
    And The response message code is "OK"
    Then The longitude value is "<longitude>"
    And The latitude value is "<latitude>"
    Examples:
    | city        | longitude | latitude |
    | London      | -0.1257   | 51.5085  |
    | Bucharest   | 26.1063   |44.4323   |


  @Smoke
  Scenario: Check that the Weather API responds with 200 when using a valid city id and the data returned is correct
    Given A request is performed to the "weather" endpoint
    And Using as city id "2643743"
    When The GET http request is performed
    Then The response status code is "OK"
    And The response message code is "OK"
    Then The longitude value is "-0.1257"
    And The latitude value is "51.5085"


  Scenario: Check that the Weather API responds with 200 when using a valid longitude and latitude and the data returned is correct
    Given A request is performed to the "weather" endpoint
    And Using as city longitude "-0.13"
    And Using as city latitude "51.51"
    When The GET http request is performed
    Then The response status code is "OK"
    And The response message code is "OK"
    And The city name is "London"

    @Bug
    Scenario: Check that the Weather API responds with 200 when using a valid city id and the data is correct
      Given A request is performed to the "weather" endpoint
      And Using as city id "2643743"
      When The GET http request is performed
      Then The response status code is "OK"
      And The response message code is "OK"
      Then The longitude value is "-0.13"
      And The latitude value is "51.51"