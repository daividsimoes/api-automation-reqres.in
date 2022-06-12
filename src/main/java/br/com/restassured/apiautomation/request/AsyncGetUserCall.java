package br.com.restassured.apiautomation.request;

import br.com.restassured.apiautomation.model.response.GetResponseObject;
import br.com.restassured.apiautomation.model.response.user.UserResponse;
import br.com.restassured.apiautomation.service.UserService;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class AsyncGetUserCall extends Thread implements Serializable {

    private RequestUtil requestUtil;
    private UserService userService;
    private String userId;
    private GetResponseObject<UserResponse> userResponse;

    public AsyncGetUserCall(String userId) {
        this.requestUtil = new RequestUtil();
        this.userService = new UserService();
        this.userId = userId;
    }

    @Override
    public void run() {
        userResponse = userService.getUserById(userId);
    }
}
