package com.luna.userapi.repository;

import com.luna.userapi.models.User;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findUsersByDateRange(String startDate, String endDate);

    List<User> findUsersByProfession(String profession);

    Optional<User> findUserById(Integer id);

    Optional<User> findUserByFullName(String firstName, String lastName);

}
