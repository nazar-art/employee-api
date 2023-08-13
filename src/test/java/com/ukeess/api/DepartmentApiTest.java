package com.ukeess.api;

import com.ukeess.utils.SecurityUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.ukeess.security.constant.SecurityConstants.AUTHORIZATION_HEADER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author Nazar Lelyak.
 */

public class DepartmentApiTest extends AbstractApiTest {

    @Test
    void ifDepartmentsAreFoundThenTheyAreReturned() {
        given()
            .log().all()
            .contentType(ContentType.JSON)
            .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
        .when()
            .get("/api/v1/departments")
        .then()
            .log().body()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", is(4));
    }

    @Test
    void ifSeparateDepartmentIsFoundThenItIsReturned() {
        given()
            .log().all()
            .contentType(ContentType.JSON)
            .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
        .when()
            .get("/api/v1/departments/1")
        .then()
            .log().body()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("id", is(1))
            .body("name", equalTo("Gryffindor"));
    }
}
