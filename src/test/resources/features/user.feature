Feature: User feature

  # Note: This is not a perform test.
  # As indicated by the docs the value is not precise, especially when the JVM is cold.
  # You should regard the time measurement as an approximation rather than fact.
  # For example it can be useful to track statistics (on a CI server) over longer periods of time to find
  # deviations such as sudden spikes related to a specific commit.
  # The reason why you should not regard this as a precise measurement is because REST Assured's response time
  # measurement includes a lot of processing such JVM/Groovy class load times
  Scenario Outline: Validate response time is no longer than '1' second when using delay
    Given I call get users API with delay <delay>
    Then response time should be no longer than '1' second
    Examples:
      | delay |
      | "3"   |
      | "0"   |

  Scenario: Get a list of users
    Given I call get users API
    Then response list should contains status code 200
    And should print odd ID numbers

  Scenario: Create a new user
    Given I have a new user data performed
    When I call add user API
    Then add or update user response should contains status code 201
    And creation date should be today

  Scenario: Update a user
    Given I have one user already created
    And I have a new user data performed
    When I call update user API
    Then add or update user response should contains status code 200
    And should update user successfully

  Scenario: Get '10' single users in asynchronous calls
    Given I call get user by id API to get '10' users asynchronous
    Then all response code should be 200