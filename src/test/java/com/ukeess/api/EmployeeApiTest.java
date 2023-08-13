package com.ukeess.api;

import com.ukeess.rest.dto.EmployeeDTO;
import com.ukeess.utils.SecurityUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.ukeess.security.constant.SecurityConstants.AUTHORIZATION_HEADER;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Nazar Lelyak.
 */
public class EmployeeApiTest extends AbstractApiTest {

    @Test
    void ifEmployeesAreFoundThenTheyAreReturned() {
        given()
            .log().all()
            .contentType(ContentType.JSON)
            .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
        .when()
            .get("/api/v1/employees")
        .then()
            .log().body()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("content.size()", is(3));
    }

    @Test
    void ifEmployeeIsFoundThenItIsReturned() {
        given()
            .log().all()
            .contentType(ContentType.JSON)
            .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
        .when()
            .get("/api/v1/employees/2")
        .then()
            .log().body()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("id", is(2))
            .body("name", equalTo("Luna"))
            .body("active", is(false))
            .body("departmentId", is(2))
            .body("departmentName", equalTo("Hufflepuff"))
            .body("createdAt", notNullValue())
            .body("updatedAt", notNullValue());
    }

    @Test
    void ifSearchByBeginningOfNameItReturnCorrectEmployee() {
        given()
            .log().all()
            .contentType(ContentType.JSON)
            .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
        .when()
            .get("/api/v1/employees/search?name=r")
        .then()
            .log().body()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("content.size()", is(1))
            .body("content[0].id", is(3))
            .body("content[0].name", equalTo("Robert"))
            .body("content[0].active", is(false))
            .body("content[0].departmentId", is(3))
            .body("content[0].departmentName", equalTo("Ravenclaw"))
            .body("content[0].createdAt", notNullValue())
            .body("content[0].updatedAt", notNullValue());
    }

    @Test
    void ifPostValidRequestBodyThanNewEmployeeInstanceIsCreated() {
        var requestBody = """
                {
                  "name": "Jordano Bruno",
                  "departmentId": 3,
                  "active": false,
                  "departmentName": "Ravenclaw"
                }
                """;

        given()
            .log().all()
            .contentType(ContentType.JSON)
            .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
            .body(requestBody)
        .when()
            .post("/api/v1/employees")
        .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Location", endsWith("/api/v1/employees/4"))
            .body("id", is(4))
            .body("name", equalTo("Jordano Bruno"))
            .body("active", is(false))
            .body("departmentId", is(3))
            .body("departmentName", equalTo("Ravenclaw"))
            .body("createdAt", notNullValue())
            .body("updatedAt", notNullValue());
    }

    @Test
    void ifPutValidEmployeeThanItWillUpdated() {
        String[] comparisonFields = {"id", "name", "active", "departmentId", "departmentName"};

        var expectedBeforeUpdate = EmployeeDTO.builder()
                .id(2)
                .name("Luna")
                .active(false)
                .departmentId(2)
                .departmentName("Hufflepuff")
                .build();

        // validate before update
        assertThat(getEmployeeDTO(2))
                .isEqualToComparingOnlyGivenFields(
                        expectedBeforeUpdate,
                        comparisonFields
                );

        // update employee
        var updated = given()
            .log().all()
            .contentType(ContentType.JSON)
            .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
            .body("""
                   {
                     "name": "Christopher Columbus",
                     "departmentId": 4,
                     "active": true,
                     "departmentName": "Slytherin"
                   }
                   """)
        .when()
            .put("/api/v1/employees/2")
        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract().as(EmployeeDTO.class);

        var expectedUpdated = EmployeeDTO.builder()
                .id(2)
                .name("Christopher Columbus")
                .active(true)
                .departmentId(4)
                .departmentName("Slytherin")
                .build();
        assertThat(updated).isEqualToComparingOnlyGivenFields(
                expectedUpdated,
                comparisonFields
        );

        // validate after update
        assertThat(getEmployeeDTO(2)).isEqualToComparingOnlyGivenFields(
                expectedUpdated,
                comparisonFields
        );
    }

    private static EmployeeDTO getEmployeeDTO(int id) {
        return given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header(AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                .when()
                    .get("/api/v1/employees/" + id)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.OK.value())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .extract().as(EmployeeDTO.class);
    }


    // todo cover rest of api
}
