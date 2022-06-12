package br.com.restassured.apiautomation.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponseObject<T> implements Serializable {

    private T data;
    private int statusCode;
    private long responseTime;
}
