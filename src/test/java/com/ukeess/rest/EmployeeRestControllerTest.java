package com.ukeess.rest;

import com.ukeess.config.CommonErrorHandler;
import com.ukeess.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nazar Lelyak.
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(value = CommonErrorHandler.class)
@WithMockUser(roles = "ADMIN")
@AutoConfigureMockMvc
class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(value = {"/sql/truncate-data-before.sql"}, executionPhase = BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/truncate-data-after.sql"}, executionPhase = AFTER_TEST_METHOD)
    void createEmployee() throws Exception {
        mockMvc.perform(post("/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "name": "test_name",
                                  "departmentId": 5,
                                  "active": true,
                                  "departmentName": "test_department"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "name": "test_name",
                            "active": true,
                            "departmentId": 5,
                            "departmentName": "test_department"
                        }
                        """));
    }

    @Test
    @Sql(value = {"/sql/populate-data-before.sql"}, executionPhase = BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/truncate-data-after.sql"}, executionPhase = AFTER_TEST_METHOD)
    void findEmployee() throws Exception {
        mockMvc.perform(get("/v1/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Hagrid"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.departmentId").value(1))
                .andExpect(jsonPath("$.departmentName").value("Gryffindor"))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.updatedAt").isNotEmpty())
        ;
    }

    @Test
    @Sql(value = {"/sql/populate-data-before.sql"}, executionPhase = BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/truncate-data-after.sql"}, executionPhase = AFTER_TEST_METHOD)
    void findAllEmployees() throws Exception {
        mockMvc.perform(get("/v1/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(FileUtils.readFromFile("employees-response.json")))
        ;
    }

    @Test
    @Sql(value = {"/sql/populate-data-before.sql"}, executionPhase = BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/truncate-data-after.sql"}, executionPhase = AFTER_TEST_METHOD)
    void updateEmployees() throws Exception {

        mockMvc.perform(get("/v1/employees/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "id": 3,
                            "name": "Robert",
                            "active": false,
                            "departmentId": 3,
                            "departmentName": "Ravenclaw"
                        }
                        """))
        ;

        mockMvc.perform(put("/v1/employees/3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "name": "Jordano Bruno",
                                    "active": false,
                                    "departmentId": 2,
                                    "departmentName": "Hufflepuff"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "id": 3,
                            "name": "Jordano Bruno",
                            "active": false,
                            "departmentId": 2,
                            "departmentName": "Hufflepuff"
                        }
                        """))
        ;
    }


}