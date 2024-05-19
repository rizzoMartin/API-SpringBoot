package com.example.demo.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    public UserModel saveUser(@Valid @RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@Valid @RequestBody UserModel user, @PathVariable Long id) {
        return this.userService.updateUserById(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteUserById(@PathVariable Long id) {
        boolean ok = this.userService.deleteUserById(id);
        if(ok) {
            System.out.println("User with id [" + id + "] successfully deleted");
            return true;
        } else {
            System.out.println("Error: Can't delete user with id [" + id + "]");
            return false;
        }
    }
}
