package com.ukeess.e2e;

import com.ukeess.security.constant.SecurityConstants;
import com.ukeess.utils.FileUtils;
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
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllEmployees() throws Exception {
        mockMvc.perform(get("/v1/employees")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(FileUtils.readFromFile("employees-response.json")))
        ;
    }

    @Test
    void getFirstEmployees() throws Exception {
        String expected = """
                {
                  "id": 1,
                  "name": "Hagrid",
                  "active": true,
                  "departmentId": 1,
                  "departmentName": "Gryffindor"
                }
                """;


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
        mockMvc.perform(post("/v1/employees")
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                {
                  "name": "Fernando Magelan",
                  "departmentId": 3,
                  "active": true,
                  "departmentName": "Ravenclaw"
                }
                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(FileUtils.readFromFile("new-employee-response.json")))
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
                .andExpect(content().json(FileUtils.readFromFile("update-employee-response.json")))
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
