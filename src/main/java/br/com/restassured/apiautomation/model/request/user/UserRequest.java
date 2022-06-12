package br.com.restassured.apiautomation.model.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserRequest implements Serializable {

    private String name;
    private String job;
}
