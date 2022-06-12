package br.com.restassured.apiautomation.model.response.user;

import br.com.restassured.apiautomation.model.response.ResponseObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddOrUpdateUserResponse extends ResponseObject implements Serializable {

    private int id;
    private String name;
    private String job;
    private LocalDateTime createdAt;
}
