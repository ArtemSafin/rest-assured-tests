package test;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTests {

    @Test
    void successfulLoginTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        String urlData = "https://reqres.in/api/login";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .log().body()

                .when()
                .post(urlData)

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulLoginTestWithoutPassword() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"\"}";
        String urlData = "https://reqres.in/api/login";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .log().body()

                .when()
                .post(urlData)

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void unsuccessfulLoginTestWithoutEmail() {
        String authData = "{\"email\": \"\", \"password\": \"cityslicka\"}";
        String urlData = "https://reqres.in/api/login";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .log().body()

                .when()
                .post(urlData)

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));

    }

    @Test
    void unsuccessfulLoginTestWrongUsername() {
        String authData = "{\"email\": \"eve666.holt@reqres.in\", \"password\": \"cityslicka\"}";
        String urlData = "https://reqres.in/api/login";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .log().body()

                .when()
                .post(urlData)

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));

    }

}
