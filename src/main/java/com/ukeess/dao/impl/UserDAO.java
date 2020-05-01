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
public class UserDAO extends NamedParameterJdbcDaoSupport implements BaseDAO {

    @Autowired
    public void setJt(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    private RowMapper<AuthUser> getUserRowMapper() {
        return (rs, rowNum) -> AuthUser.builder()
                .id(rs.getInt("id"))
                .userName(rs.getString("username"))
                .password(rs.getString("password"))
                .active(rs.getBoolean("active"))
                .role(rs.getString("role"))
                .build();
    }

    @Override
    public Object save(Object entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page getAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException();
    }

    public Optional<AuthUser> findUserByUserName(String userName) {
        return Optional.of(getNamedParameterJdbcTemplate()
                .queryForObject("SELECT * FROM tblUsers WHERE username=:name",
                        new MapSqlParameterSource("name", userName),
                        getUserRowMapper()));
    }
}
