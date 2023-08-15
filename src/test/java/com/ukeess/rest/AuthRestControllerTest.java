package com.ukeess.rest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nazar Lelyak.
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = {"/sql/add-admin-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/eliminate-admin-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AuthRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void generateToken_Success() throws Exception {
        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "username": "harry",
                                  "password": "potter"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.token").isString());
    }

    @ParameterizedTest
    @MethodSource("invalidParams")
    void generateToken_InvalidParams(String name, String nameVal, String pass, String passVal) throws Exception {
        JSONObject json = new JSONObject();
        json.put(name, nameVal);
        json.put(pass, passVal);

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json.toString()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    private static Stream<Arguments> invalidParams() {
        return Stream.of(
                Arguments.of("name", "hello",                       "pass", "world"),
                Arguments.of("",     "hello",                       "pass", "world"),
                Arguments.of("name", "hello",                       "",     "world"),
                Arguments.of("name", "h",                           "pass", "world"),
                Arguments.of("name", "hello",                       "pass", "wor"),
                Arguments.of("name", "itisaverylongusernametoolong","pass", "world"),
                Arguments.of("name", "hello",                       "pass", "verylonguserpassword")
        );
    }
}