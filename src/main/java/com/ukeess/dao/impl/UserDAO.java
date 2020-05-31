package com.ukeess.dao.impl;

import com.ukeess.dao.BaseDAO;
import com.ukeess.dao.GenericDAO;
import com.ukeess.entity.impl.AuthUser;
import com.ukeess.model.dto.TableData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class UserDAO extends BaseDAO<AuthUser> implements GenericDAO<AuthUser> {

    private static final TableData userTable = TableData.builder()
            .tableName("tblUsers")
            .id("userID")
            .name("userName")
            .field("userPassword")
            .field("userActive")
            .field("userRole")
            .build();

    public UserDAO() {
        super(userTable);
    }

    @Override
    protected RowMapper<AuthUser> getRowMapper() {
        return (rs, rowNum) -> AuthUser.builder()
                .id(rs.getInt("userID"))
                .name(rs.getString("userName"))
                .password(rs.getString("userPassword"))
                .active(rs.getBoolean("userActive"))
                .role(rs.getString("userRole"))
                .build();
    }
}
