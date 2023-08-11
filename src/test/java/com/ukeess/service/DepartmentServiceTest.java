package com.ukeess.service;

import com.ukeess.entity.Department;
import com.ukeess.repository.DepartmentRepository;
import com.ukeess.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * @author Nazar Lelyak.
 */
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    private static final int DEPARTMENT_ID_ONE = 11;
    private static final int DEPARTMENT_ID_TWO = 22;
    private static final String DEPARTMENT_NAME_ONE = "test_department_name_one";
    private static final String DEPARTMENT_NAME_TWO = "test_department_name_two";

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void findAllDepartments() {
        List<Department> departmentList = List.of(Department.builder()
                        .id(DEPARTMENT_ID_ONE)
                        .name(DEPARTMENT_NAME_ONE)
                        .build(),
                Department.builder()
                        .id(DEPARTMENT_ID_TWO)
                        .name(DEPARTMENT_NAME_TWO)
                        .build());
        when(departmentRepository.findAll())
                .thenReturn(departmentList);

        assertThat(departmentService.findAllDepartments())
                .isEqualTo(departmentList);

        verify(departmentRepository).findAll();
        verifyNoMoreInteractions(departmentRepository);
    }

    @Test
    void findById() {
        var departmentOptional = Optional.of(Department.builder()
                .id(DEPARTMENT_ID_ONE)
                .name(DEPARTMENT_NAME_ONE)
                .build());
        when(departmentRepository.findById(anyInt()))
                .thenReturn(departmentOptional);

        assertThat(departmentService.findById(DEPARTMENT_ID_ONE))
                .isEqualTo(departmentOptional);

        verify(departmentRepository).findById(DEPARTMENT_ID_ONE);
        verifyNoMoreInteractions(departmentRepository);
    }

}