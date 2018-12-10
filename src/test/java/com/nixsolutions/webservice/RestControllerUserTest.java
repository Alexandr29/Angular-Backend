package com.nixsolutions.webservice;

import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.User;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;

class RestControllerUserTest {
    RestControllerUser rest;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

    }

    @org.junit.jupiter.api.Test
    void getUser() {
        RestAssured.get("/rest/users/1")
                .then()
                .body("id", equalTo("1"))
                .body("firstName", equalTo("Alexandr"))
                .body("lastName", equalTo("Sinkevich"))
                .body("roleId", equalTo("1"))
                .and().statusCode(200);
    }

    @org.junit.jupiter.api.Test
    void getNullUser() {
        RestAssured.get("/rest/users/2")
                .then()
                .assertThat()
                .statusCode(204);
    }

    @org.junit.jupiter.api.Test
    void postUser() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "someRandomString");
        requestBody.put("lastName", "someRandomString");
        requestBody.put("login", "someRandomString");
        requestBody.put("password", "someRandomString");
        requestBody.put("email", "someRandomString" + "@gmail.com");
        requestBody.put("birthday", "1111-11-11");
        requestBody.put("roleId", 2L);

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString())
                .post("http://localhost:8080/rest/users")
                .then()
                .statusCode(204);


    }

    @org.junit.jupiter.api.Test
    void putUser() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "String");
        requestBody.put("lastName", "String");
        requestBody.put("login", "someRandomString");
        requestBody.put("password", "String");
        requestBody.put("email", "String" + "@gmail.com");
        requestBody.put("birthday", "1111-11-11");
        requestBody.put("roleId", 1L);

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.body(requestBody.toString())
                .put("http://localhost:8080/rest/users/11345")
                .then()
                .statusCode(204);

    }

    @org.junit.jupiter.api.Test
    void deleteUser() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.delete("http://localhost:8080/rest/users/11239");
        request.then().statusCode(200);
    }

    @BeforeEach
    void setUp() {
        rest = new RestControllerUser();
    }
}