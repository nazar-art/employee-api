package com.ukeess.dao;

import com.ukeess.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
public abstract class BaseDAO<E extends BaseEntity> extends NamedParameterJdbcDaoSupport implements GenericDAO<E> {

    @Autowired
    public void setJt(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    private final String tableName;
    private final String tableIdName;
    private final String insertSQL;
    private final String updateSQL;

    public BaseDAO(String tableName, String tableIdName, List<String> fields) {
        this.tableName = tableName;
        this.tableIdName = tableIdName;

        // init SQLs
        StringBuilder sbInsertSQL = new StringBuilder();
        StringBuilder sbUpdateSQL = new StringBuilder();

        sbInsertSQL.append("INSERT INTO ").append(tableName).append(" (");
        sbUpdateSQL.append("UPDATE ").append(tableName).append(" SET ");

        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) {
                sbInsertSQL.append(", ");
                sbUpdateSQL.append(", ");
            }
            sbInsertSQL.append(fields.get(i));
            sbUpdateSQL.append(fields.get(i)).append("=:").append(fields.get(i));
        }

        sbInsertSQL.append(") ").append("VALUES (");
        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) {
                sbInsertSQL.append(",");
            }
            sbInsertSQL.append(":").append(fields.get(i));
        }
        sbInsertSQL.append(")");
        sbUpdateSQL.append(String.format(" WHERE %s=:id", tableIdName));

        this.insertSQL = sbInsertSQL.toString();
        this.updateSQL = sbUpdateSQL.toString();

        log.debug("INSERT_SQL: [{}]", insertSQL);
        log.debug("UPDATE_SQL: [{}]", updateSQL);
    }

    protected abstract RowMapper<E> getRowMapper();

    @Override
    public E save(E entity) {
        if (entity.getId() == null) {
            return insert(entity);
        } else {
            update(entity);
        }
        return entity;
    }

    private E insert(E entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        getNamedParameterJdbcTemplate()
                .update(insertSQL, new BeanPropertySqlParameterSource(entity), keyHolder);

        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    protected void update(E entity) {
        getNamedParameterJdbcTemplate()
                .update(updateSQL, new BeanPropertySqlParameterSource(entity));
    }

    @Override
    public Optional<E> getById(int id) {
        return Optional.of(getNamedParameterJdbcTemplate()
                .queryForObject(
                        String.format("SELECT * FROM %s WHERE %s=:id", tableName, tableIdName),
                        getIdParameterSource(id),
                        getRowMapper()
                ));
    }


    @Override
    public Optional<E> getByName(String name) {
        return Optional.of(getNamedParameterJdbcTemplate()
                .queryForObject(
                        String.format("SELECT * FROM %s WHERE name=:name", tableName),
                        getNameParameterSource(name),
                        getRowMapper()
                ));
    }


    @Override
    public void deleteById(int id) {
        getNamedParameterJdbcTemplate()
                .update(String.format("DELETE FROM %s WHERE id=:id", tableName),
                        getIdParameterSource(id));
    }

    @Override
    public Page<E> getAll(Pageable pageable) {
        List<E> result = getJdbcTemplate()
                .query(String.format("SELECT * " +
                                "FROM %s " +
                                "ORDER BY id " +
                                "LIMIT %s OFFSET %s",
                        tableName,
                        pageable.getPageSize(), pageable.getOffset()),
                        new String[] {},
                        getRowMapper());
        return new PageImpl<>(result, pageable, getTotalRows());
    }

    private long getTotalRows() {
        return getJdbcTemplate()
                .queryForObject(String.format("select count(*) from %s", tableName), long.class);
    }
}
