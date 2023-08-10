package com.ukeess.e2e;

import com.ukeess.security.constant.SecurityConstants;
import com.ukeess.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.ukeess.utils.FileUtils.readFromFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nazar Lelyak.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Sql(value = {"/sql/populate-data-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/truncate-data-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllDepartments() throws Exception {
        mockMvc.perform(get("/v1/departments")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(readFromFile("departments-response.json")))
        ;
    }

    @Test
    void getFirstDepartment() throws Exception {
        String expected = "{\"id\":1,\"name\":\"Gryffindor\"}";

        mockMvc.perform(get("/v1/departments/1")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expected))
        ;
    }

    @Test
    void getAllEmployees() throws Exception {
        mockMvc.perform(get("/v1/employees")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(readFromFile("employees-response.json")))
        ;
    }

    @Test
    void getFirstEmployees() throws Exception {
        String expected = "{\"id\":1,\"name\":\"Hagrid\",\"active\":true,\"departmentId\":1,\"departmentName\":\"Gryffindor\"}";

        mockMvc.perform(get("/v1/employees/1")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expected))
        ;
    }

    @Test
    void postEmployee() throws Exception {
        String requestJson = """
                {
                  "name": "Fernando Magelan",
                  "departmentId": 3,
                  "active": true,
                  "departmentName": "Ravenclaw"
                }
                """;

        mockMvc.perform(post("/v1/employees")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(readFromFile("new-employee-response.json")))
        ;
    }

    @Test
    void updateEmployee() throws Exception {
        String requestJson = """
                {
                  "id": 3,
                  "name": "Christopher Columbus",
                  "departmentId": 2,
                  "active": false,
                  "departmentName": "Hufflepuff"
                }
                """;

        mockMvc.perform(put("/v1/employees/3")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(readFromFile("update-employee-response.json")))
        ;
    }

    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(delete("/v1/employees/3")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                )
                .andDo(print())
                .andExpect(status().isNoContent())
        ;
    }

}
