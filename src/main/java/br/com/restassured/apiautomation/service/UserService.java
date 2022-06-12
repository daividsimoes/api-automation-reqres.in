package br.com.restassured.apiautomation.service;

import br.com.restassured.apiautomation.model.request.user.UserRequest;
import br.com.restassured.apiautomation.model.response.GetResponseListObject;
import br.com.restassured.apiautomation.model.response.GetResponseObject;
import br.com.restassured.apiautomation.model.response.user.AddOrUpdateUserResponse;
import br.com.restassured.apiautomation.model.response.user.UserResponse;
import br.com.restassured.apiautomation.request.RequestUtil;

public class UserService {

    private RequestUtil requestUtil;
    private final String LIST_USERS = "/api/users?per_page={0}&delay={1}";
    private final String CREATE_USER = "/api/users";
    private final String USER_BY_ID = "/api/users/{0}";

    public UserService() {

        this.requestUtil = new RequestUtil();
    }

    public GetResponseListObject<UserResponse> getUserList() {

        return requestUtil.getList(UserResponse.class, LIST_USERS, "1000", "0");
    }

    public GetResponseListObject<UserResponse> getUserList(String delay) {

        return requestUtil.getList(UserResponse.class, LIST_USERS, "1000", delay);
    }

    public GetResponseObject<UserResponse> getUserById(String id) {

        return requestUtil.get(UserResponse.class, USER_BY_ID, id);
    }

    public AddOrUpdateUserResponse addUser(UserRequest userRequest) {

        return requestUtil.post(userRequest, AddOrUpdateUserResponse.class, CREATE_USER);
    }

    public AddOrUpdateUserResponse updateUser(UserRequest userRequest, int userId) {

        return requestUtil.put(userRequest, AddOrUpdateUserResponse.class, USER_BY_ID, userId);
    }
}
