package br.com.restassured.apiautomation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User {

    private String name;
    private String job;
}
