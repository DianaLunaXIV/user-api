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
    public List<User> findUsersByDateRange(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<User> findUsersByProfession(String profession) {
        try {
            List<User> found = namedParameterJdbcTemplate.query(
                "SELECT * FROM users WHERE profession = :profession",
                    Map.of("profession", profession),
                    new DataClassRowMapper<>(User.class)
            );
            return found;
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
    public Optional<User> findUserByName(String name) {
        return Optional.empty();
    }

}
