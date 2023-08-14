package com.ukeess.rest;

import com.ukeess.config.CommonErrorHandler;
import com.ukeess.entity.Department;
import com.ukeess.security.constant.SecurityConstants;
import com.ukeess.service.DepartmentService;
import com.ukeess.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nazar Lelyak.
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(value = CommonErrorHandler.class)
@Sql(value = {"/sql/add-admin-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/eliminate-admin-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DepartmentRestControllerTest {

    private static final int DEPARTMENT_ID_ONE = 11;
    private static final int DEPARTMENT_ID_TWO = 22;
    private static final String DEPARTMENT_NAME_ONE = "test_department_name_one";
    private static final String DEPARTMENT_NAME_TWO = "test_department_name_two";
    private static final String DEPARTMENTS_URI = "/v1/departments";
    private static final String DEPARTMENT_URI = String.format("%s/%s", DEPARTMENTS_URI, DEPARTMENT_ID_ONE);

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void findAllDepartments_Success() throws Exception {
        when(departmentService.findAllDepartments())
                .thenReturn(List.of(Department.builder()
                                .id(DEPARTMENT_ID_ONE)
                                .name(DEPARTMENT_NAME_ONE)
                                .build(),
                        Department.builder()
                                .id(DEPARTMENT_ID_TWO)
                                .name(DEPARTMENT_NAME_TWO)
                                .build()));

        mockMvc.perform(get(DEPARTMENTS_URI)
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(11))
                .andExpect(jsonPath("$[0].name").value("test_department_name_one"))
                .andExpect(jsonPath("$[1].id").value(22))
                .andExpect(jsonPath("$[1].name").value("test_department_name_two"));

        verify(departmentService).findAllDepartments();
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    void findAllDepartments_WithoutToken() throws Exception {
        mockMvc.perform(get(DEPARTMENTS_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
        verifyNoInteractions(departmentService);
    }

    @Test
    void findAllDepartments_WithWrongToken() throws Exception {
        mockMvc.perform(get(DEPARTMENTS_URI)
                        .header(
                                SecurityConstants.AUTHORIZATION_HEADER,
                                SecurityUtils.generateToken("some-incorrect-name", false)
                        )
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
        verifyNoInteractions(departmentService);
    }

    @Test
    void findAllDepartments_WithExpiredToken() throws Exception {
        mockMvc.perform(get(DEPARTMENTS_URI)
                        .header(
                                SecurityConstants.AUTHORIZATION_HEADER,
                                SecurityUtils.generateExpiredToken()
                        )
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());

        verifyNoInteractions(departmentService);
    }

    @Test
    void findDepartmentById_Success() throws Exception {
        when(departmentService.findById(anyInt()))
                .thenReturn(Optional.of(Department.builder()
                        .id(DEPARTMENT_ID_ONE)
                        .name(DEPARTMENT_NAME_ONE)
                        .build()));

        mockMvc.perform(get(DEPARTMENT_URI)
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.name").value("test_department_name_one"));

        verify(departmentService).findById(DEPARTMENT_ID_ONE);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    void findDepartmentById_WithoutToken() throws Exception {
        mockMvc.perform(get(DEPARTMENT_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
        verifyNoInteractions(departmentService);
    }

    @Test
    void findDepartmentById_WithWrongToken() throws Exception {
        mockMvc.perform(get(DEPARTMENT_URI)
                        .header(SecurityConstants.AUTHORIZATION_HEADER, SecurityUtils.generateToken("Tomas", false))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
        verifyNoInteractions(departmentService);
    }

    @Test
    void findDepartmentById_WithExpiredToken() throws Exception {
        mockMvc.perform(get(DEPARTMENT_URI)
                        .header(
                                SecurityConstants.AUTHORIZATION_HEADER,
                                SecurityUtils.generateExpiredToken()
                        )
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());

        verifyNoInteractions(departmentService);
    }

}