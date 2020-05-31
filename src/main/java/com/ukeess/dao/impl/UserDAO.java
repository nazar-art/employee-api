package com.ukeess.dao.impl;

import com.ukeess.dao.BaseDAO;
import com.ukeess.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Repository
public class UserDAO extends NamedParameterJdbcDaoSupport implements BaseDAO<AuthUser> {

    @Autowired
    public void setJt(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    private RowMapper<AuthUser> getUserRowMapper() {
        return (rs, rowNum) -> AuthUser.builder()
                .id(rs.getInt("userID"))
                .userName(rs.getString("userName"))
                .password(rs.getString("userPassword"))
                .active(rs.getBoolean("userActive"))
                .role(rs.getString("userRole"))
                .build();
    }

    @Override
    public AuthUser save(AuthUser entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AuthUser> getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<AuthUser> getAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException();
    }

    public Optional<AuthUser> findUserByUserName(String userName) {
        return Optional.of(getNamedParameterJdbcTemplate()
                .queryForObject("SELECT userID, userName, userPassword, userActive, userRole FROM tblUsers WHERE userName=:name",
                        new MapSqlParameterSource("name", userName),
                        getUserRowMapper()));
    }
}
