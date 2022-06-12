package br.com.restassured.apiautomation.factory;

import br.com.restassured.apiautomation.model.request.user.UserRequest;
import br.com.restassured.apiautomation.util.FakerUtil;
import lombok.Builder;

public class UserFactory {

    private FakerUtil fakerUtil;

    public UserFactory() {

        fakerUtil = new FakerUtil();
    }

    public UserRequest buildUserRequest() {

        return UserRequest.builder()
                .name(fakerUtil.generateRandomName())
                .job(fakerUtil.generateRandomJob())
                .build();
    }
}
