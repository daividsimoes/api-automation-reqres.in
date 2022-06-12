package br.com.restassured.apiautomation.request;

import br.com.restassured.apiautomation.model.response.GetResponseObject;
import br.com.restassured.apiautomation.model.response.GetResponseListObject;
import br.com.restassured.apiautomation.model.response.ResponseObject;
import br.com.restassured.apiautomation.util.JsonUtil;
import br.com.restassured.apiautomation.util.UrlUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;

import static io.restassured.RestAssured.given;

@Slf4j
public class RequestUtil {

    private String url;

    public <T extends ResponseObject> T post(Object body, Class<T> clazz, String endpoint, Object... args) {

        url = UrlUtil.buildUrl(endpoint, args);

        log.info("REQUEST -> Executing POST on: {}", url);
        log.info("REQUEST -> BODY: {}", JsonUtil.convertToJson(body));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(JsonUtil.convertToJson(body))
                .post(url);

        return convertResponseToObject(response, clazz);
    }

    public <T extends ResponseObject> T put(Object body, Class<T> clazz, String endpoint, Object... args) {

        url = UrlUtil.buildUrl(endpoint, args);

        log.info("REQUEST -> Executing PUT on: {}", url);
        log.info("REQUEST -> BODY: {}", JsonUtil.convertToJson(body));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(JsonUtil.convertToJson(body))
                .put(url);

        return convertResponseToObject(response, clazz);
    }

    public <T> GetResponseObject<T> get(Class<T> clazz, String endpoint,
                                        Object... args) {

        url = UrlUtil.buildUrl(endpoint, args);

        log.info("REQUEST -> Executing GET on: {}", url);

        Response response = given()
                .contentType(ContentType.JSON)
                .get(url);

        return convertGetResponseToObject(response, clazz);
    }

    public <T> GetResponseListObject<T> getList(Class<T> clazz, String endpoint,
                                                Object... args) {

        url = UrlUtil.buildUrl(endpoint, args);

        log.info("REQUEST -> Executing GET on: {}", url);

        Response response = given()
                .contentType(ContentType.JSON)
                .get(url);

        return convertGetResponseToObjectList(response, clazz);
    }

    private <T> GetResponseObject<T> convertGetResponseToObject(Response response, Class<T> clazz) {

        GetResponseObject<T> responseConverted = new GetResponseObject<>();

        log.info("RESPONSE -> StatusCode: {}", response.getStatusCode());
        log.info("RESPONSE -> Time: {}", response.getTime());
        log.info("RESPONSE -> Body: {}", response.getBody().asString());

        try {
            if (!response.getBody().asString().isEmpty()) {
                responseConverted = response.getBody().as(getTypeFromClassResponseObject(clazz));
            }
            responseConverted.setStatusCode(response.getStatusCode());
            responseConverted.setResponseTime(response.getTime());

        } catch (Exception ex) {
            throw new RuntimeException(
                    MessageFormat.format("Failure to convert response -> {0}\nException Message -> {1}",
                            response.getBody().asString(), ex.getMessage())
            );
        }

        return responseConverted;
    }

    private <T> GetResponseListObject<T> convertGetResponseToObjectList(Response response, Class<T> clazz) {

        log.info("RESPONSE -> StatusCode: {}", response.getStatusCode());
        log.info("RESPONSE -> Time: {}", response.getTime());
        log.info("RESPONSE -> Body: {}", response.getBody().asString());

        GetResponseListObject<T> responseConverted = new GetResponseListObject<>();

        try {
            if (!response.getBody().asString().isEmpty()) {
                responseConverted = response.getBody().as(getTypeFromClassResponseListData(clazz));
            }
            responseConverted.setStatusCode(response.getStatusCode());
            responseConverted.setResponseTime(response.getTime());

        } catch (Exception ex) {
            throw new RuntimeException(
                    MessageFormat.format("Failure to convert response -> {0}\nException Message -> {1}",
                            response.getBody().asString(), ex.getMessage())
            );
        }

        return responseConverted;
    }

    private <T extends ResponseObject> T convertResponseToObject(Response response, Class<T> clazz) {

        T responseConverted = null;

        log.info("RESPONSE -> StatusCode: {}", response.getStatusCode());
        log.info("RESPONSE -> Time: {}", response.getTime());
        log.info("RESPONSE -> Body: {}", response.getBody().asString());

        try {
            if (!response.getBody().asString().isEmpty()) {
                responseConverted = response.getBody().as(clazz);
            }
            responseConverted.setStatusCode(response.getStatusCode());
            responseConverted.setResponseTime(response.getTime());

        } catch (Exception ex) {
            throw new RuntimeException(
                    MessageFormat.format("Failure to convert response -> {0}\nException Message -> {1}",
                            response.getBody().asString(), ex.getMessage())
            );
        }

        return responseConverted;
    }

    private <T> Type getTypeFromClassResponseObject(Class<T> clazz) {

        return new ParameterizedTypeReference<GetResponseObject<T>>() {
            public Type getType() {

                return new ParameterizedTypeImpl((ParameterizedType) super.getType(), new Type[]{clazz});
            }
        }.getType();
    }

    private <T> Type getTypeFromClassResponseListData(Class<T> clazz) {

        return new ParameterizedTypeReference<GetResponseListObject<T>>() {
            public Type getType() {

                return new ParameterizedTypeImpl((ParameterizedType) super.getType(), new Type[]{clazz});
            }
        }.getType();
    }
}
