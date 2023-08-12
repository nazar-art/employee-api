package com.ukeess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * @author Nazar Lelyak.
 */
@Data
@Entity
@Table(name = "tblUsers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Integer id;

    @NotNull
    @Column(name = "userName")
    private String name;

    @ToString.Exclude
    @Column(name = "userPass")
    private String password;

    @Column(name = "userActive")
    private Boolean active;
    @Column(name = "userRole")
    private String role;
}
