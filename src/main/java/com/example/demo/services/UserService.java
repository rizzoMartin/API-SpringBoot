package com.example.demo.services;

import com.example.demo.models.UserModel;
import com.example.demo.repositories.IUserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) this.userRepository.findAll();
    };

    public Optional<UserModel> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public UserModel saveUser(UserModel user) {
        return this.userRepository.save(user);
    }

    public UserModel updateUserById(UserModel request, Long id) {
        UserModel user = this.userRepository.findById(id).get();

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return this.userRepository.save(user);
    }

    public boolean deleteUserById(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
