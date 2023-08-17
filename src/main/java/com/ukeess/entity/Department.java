package com.ukeess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nazar Lelyak.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Table(name = "tblDepartments")
@Builder
public class Department {

    @Id
    @Column(name = "dpID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 1, max = 24)
    @Column(name = "dpName")
    private String name;
}
