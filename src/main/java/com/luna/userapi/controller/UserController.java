package com.luna.userapi.controller;

import com.luna.userapi.models.User;
import com.luna.userapi.repository.UserRepository;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/api/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public @ResponseBody List<User> getByProfession(@RequestParam(name="profession") String profession) {
        List<User> usersByProfession = userRepository.findUsersByProfession(profession);
        if (usersByProfession.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Users not found by given profession.");
        }

        return usersByProfession;
    }

    @GetMapping("/dateRange")
    public @ResponseBody List<User> getByDateRange(@RequestParam(name="startDate") String startDate, @RequestParam(name="endDate") String endDate) {
        List<User> usersByRange = userRepository.findUsersByDateRange(startDate, endDate);
        if (usersByRange.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Users not found within given range of dates.");
        }

        return usersByRange;
    }

    @GetMapping("/{id}")
    public List<User> getById(@PathVariable Integer id) {
        Optional<User> userByIdOptional = userRepository.findUserById(id);
        if (userByIdOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "User not found by given ID.");
        }

        return List.of(userByIdOptional.get());
    }

    @GetMapping("/full-name")
    public @ResponseBody List<User> getByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        Optional<User> userByFullName = userRepository.findUserByFullName(firstName, lastName);
        if (userByFullName.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "User not found by given names.");
        }

        return List.of(userByFullName.get());
    }


}
