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
import javax.validation.constraints.Size;


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
    @Column(name = "usrID")
    private Integer id;

    @NotNull
    @Column(name = "usrName")
    private String name;

    @ToString.Exclude
    @Column(name = "usrPass")
    private String password;

    @Column(name = "usrActive")
    private Boolean active;
    @Column(name = "usrRole")
    private String role;
}
