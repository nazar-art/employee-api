package com.ukeess.dao.impl;

import com.google.common.collect.Lists;
import com.ukeess.dao.BaseDAO;
import com.ukeess.dao.GenericDAO;
import com.ukeess.entity.impl.AuthUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class UserDAO extends BaseDAO<AuthUser> implements GenericDAO<AuthUser> {

    public static final String USERS_TABLE_NAME = "tblUsers";
    public static final String USER_TABLE_ID = "id";
    public static final List<String> usersFields = Lists.newArrayList("name", "password", "active", "role");

    public UserDAO() {
        super(USERS_TABLE_NAME,
                USER_TABLE_ID,
                usersFields);
    }

    @Override
    protected RowMapper<AuthUser> getRowMapper() {
        return (rs, rowNum) -> AuthUser.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .password(rs.getString("password"))
                .active(rs.getBoolean("active"))
                .role(rs.getString("role"))
                .build();
    }
}
