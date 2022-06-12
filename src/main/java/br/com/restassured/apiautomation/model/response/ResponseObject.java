package br.com.restassured.apiautomation.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject implements Serializable {

    private int statusCode;
    private long responseTime;
}
