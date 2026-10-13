
package es.lobosquadrugby;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.containsString;

class ApiTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    void shouldReturnUserData() {
        given()
            .when()
                .get(BASE_URL + "/users/1")
            .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(1))
                .body("name", equalTo("Leanne Graham"));
    }

    @Test
    void shouldReturn404WhenUserDoesNotExist() {
        given()
            .when()
                .get(BASE_URL + "/users/9999")
            .then()
                .statusCode(404);
    }

}
