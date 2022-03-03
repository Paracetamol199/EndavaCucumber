@Homework
Feature: Check coordinates using WeatherAPI

  Scenario Outline: Check that the Weather API responds with 200 when using a valid city name and the data returned is correct
    Given A request is performed to the "weather" endpoint
    And Using as city name "<city>"
    When The GET http request is performed
    Then The response status code is "OK"
    And The response message code is "OK"
    Then The longitude value is "<longitude>"
    And The latitude value is "<latitude>"
    Examples:
      | city      | longitude | latitude |
      | Dublin,IE | -6.2672   | 53.344   |
      | Istanbul  | 28.9833   | 41.0351  |
      | Berlin    | 13.4105   | 52.5244  |
      | Madrid    | -3.7026   | 40.4165  |
      | Vienna    | 16.3721   | 48.2085  |

  Scenario: Check that the Weather API responds with 200 when using a valid Country name and the data returned is correct
    Given A request is performed to the "weather" endpoint
    And Using as city name "România,RO"
    When The GET http request is performed
    Then The response status code is "OK"
    And The response message code is "OK"
    Then The longitude value is "25"
    And The latitude value is "46"

  Scenario: Check that the
