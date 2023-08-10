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
                .andExpect(content().json(FileUtils.readFromFile("departments-response.json")))
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
}
