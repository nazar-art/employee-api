package com.ukeess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Nazar Lelyak.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tblEmployees")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @Column(name = "empID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 2, max = 24)
    @Column(name = "empName")
    private String name;

    @Column(name = "empActive")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "emp_dpID")
    private Department department;

    @CreatedDate
    @Column(name = "empCreatedAt")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "empUpdatedAt")
    private Date updatedAt;

}
