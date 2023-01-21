package com.luna.userapi.repository;

import com.luna.userapi.models.User;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryJDBC implements UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryJDBC(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> findUsersByDateRange(String start, String end) {
        try {
            return namedParameterJdbcTemplate.query(
                    "SELECT * FROM users WHERE date_created BETWEEN :startDate AND :endDate",
                    Map.of("endDate", Date.valueOf(end), "startDate", Date.valueOf(start)),
                    new DataClassRowMapper<>(User.class)
            );
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<User> findUsersByProfession(String profession) {
        try {
            return namedParameterJdbcTemplate.query(
                    "SELECT * FROM users WHERE profession LIKE '%" + profession.toLowerCase() + "%'",
                    new DataClassRowMapper<>(User.class)
            );

        } catch (DataAccessException e) {
            return Collections.emptyList();
        }

    }

    @Override
    public Optional<User> findUserById(Integer id) {
        try {
            User found =
                    namedParameterJdbcTemplate.queryForObject(
                            "SELECT * FROM users WHERE id = :id",
                            Map.of("id", id),
                            new DataClassRowMapper<>(User.class));
            return Optional.of(found);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByFullName(String firstName, String lastName) {
        try {
            User found = namedParameterJdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE firstName = :firstName AND lastName = :lastName",
                    Map.of("firstName", firstName, "lastName", lastName),
                    new DataClassRowMapper<>(User.class));
            return Optional.of(found);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

}
