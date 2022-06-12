package br.com.restassured.apiautomation.test.step;

import br.com.restassured.apiautomation.factory.UserFactory;
import br.com.restassured.apiautomation.model.request.user.UserRequest;
import br.com.restassured.apiautomation.model.response.GetResponseListObject;
import br.com.restassured.apiautomation.model.response.user.AddOrUpdateUserResponse;
import br.com.restassured.apiautomation.model.response.user.UserResponse;
import br.com.restassured.apiautomation.request.AsyncGetUserCall;
import br.com.restassured.apiautomation.service.UserService;
import br.com.restassured.apiautomation.util.DateUtil;
import br.com.restassured.apiautomation.util.JsonUtil;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinition {

    private UserService userService;
    private UserFactory userFactory;
    private UserRequest userRequest;
    private GetResponseListObject<UserResponse> userListResponse;
    private AddOrUpdateUserResponse addOrUpdateUserResponse;
    private AddOrUpdateUserResponse createUserResponse;
    private List<AsyncGetUserCall> asyncList = Arrays.asList(
            new AsyncGetUserCall("1"),
            new AsyncGetUserCall("2"),
            new AsyncGetUserCall("3"),
            new AsyncGetUserCall("4"),
            new AsyncGetUserCall("5"),
            new AsyncGetUserCall("6"),
            new AsyncGetUserCall("7"),
            new AsyncGetUserCall("8"),
            new AsyncGetUserCall("9"),
            new AsyncGetUserCall("10")
    );

    @Before
    public void before() {
        this.userService = new UserService();
        this.userFactory = new UserFactory();
    }

    @Given("I call get users API")
    public void i_call_get_users_API() {

        userListResponse = userService.getUserList();
    }

    @Given("I have a new user data performed")
    public void i_have_a_new_user_data_performed() {

        userRequest = userFactory.buildUserRequest();
    }

    @Given("I have one user already created")
    public void i_have_one_user_already_created() {

        createUserResponse = userService.addUser(userFactory.buildUserRequest());
    }

    @Given("I call get users API with delay {string}")
    public void i_call_get_users_API_with_delay(String delay) {

        userListResponse = userService.getUserList(delay);
    }

    @Given("I call get user by id API to get '10' users asynchronous")
    public void i_call_get_user_by_id_api_to_get_users_asynchronous() {

        asyncList.forEach(Thread::start);
        asyncList.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @When("I call add user API")
    public void i_call_add_user_API() {

        addOrUpdateUserResponse = userService.addUser(userRequest);
    }

    @When("I call update user API")
    public void i_call_update_user_API() {

        addOrUpdateUserResponse = userService.updateUser(userRequest, createUserResponse.getId());
    }

    @Then("response list should contains status code {int}")
    public void response_list_should_contains_status_code(int statusCode) {

        assertEquals(statusCode, userListResponse.getStatusCode());
    }

    @Then("add or update user response should contains status code {int}")
    public void add_or_update_user_response_should_contains_status_code(int statusCode) {

        assertEquals(statusCode, addOrUpdateUserResponse.getStatusCode());
    }

    @Then("should print odd ID numbers")
    public void should_print_odd_ID_numbers() {

        System.out.println("Users with ODD Ids");
        userListResponse.getData().forEach(user -> {
            if (user.getId() % 2 != 0) {
                System.out.println(JsonUtil.convertToJson(user));
            }
        });
    }

    @Then("creation date should be today")
    public void creation_date_should_be_today() {

        String creationDate = DateUtil.getDateToString(addOrUpdateUserResponse.getCreatedAt());
        assertEquals(DateUtil.getCurrentDateToString(), creationDate);
    }

    @Then("should update user successfully")
    public void should_update_user_successfully() {

        assertEquals(userRequest.getName(), addOrUpdateUserResponse.getName());
        assertEquals(userRequest.getJob(), addOrUpdateUserResponse.getJob());
    }

    @Then("response time should be no longer than '1' second")
    public void response_time_should_be_no_longer_than_second() {

        long seconds = DateUtil.convertMilliSecondsToSeconds(userListResponse.getResponseTime());
        assertTrue(seconds <= 1);
    }

    @Then("all response code should be {int}")
    public void all_response_code_should_be(int statusCode) {

        asyncList.forEach(item -> {
            assertEquals(statusCode, item.getUserResponse().getStatusCode());
        });
    }
}
