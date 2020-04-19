package com.ukeess.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nazar Lelyak.
 */
@Data
@Entity
@Table(name = "tblEmployees")
@ApiModel(description = "Employee entity")
public class Employee {

    @Id
    @Column(name = "empID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique id for employee")
    private Integer id;

    @NotEmpty
    @Size(min = 1, max = 25)
    @Column(name = "empName", length = 25)
    @ApiModelProperty(notes = "The employee name")
    private String name;

    @Column(name = "empActive")
    @ApiModelProperty(notes = "The employee's active status")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "emp_dpID")
    @ApiModelProperty(notes = "The employee's department")
    private Department department;
}
