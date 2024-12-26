package test;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class StatusTest {
    /*
    1. Make request to https://selenoid.autotests.cloud/status
    2. Get response {"total":20,"used":0,"queued":0...
    3. Check total is 20
     */

    @Test
    void checkTotalWithLogs() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithResponseLogs() {
        given()
                .log().all()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(21));
    }
}