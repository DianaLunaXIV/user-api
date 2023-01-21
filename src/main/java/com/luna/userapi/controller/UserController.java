package com.luna.userapi.controller;

import com.luna.userapi.models.User;
import com.luna.userapi.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        Optional<User> userByIdOptional = userRepository.findUserById(id);
        if (userByIdOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "User not found by given ID.");
        }

        return userByIdOptional.get();
    }


}
