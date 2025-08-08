package com.linhnguyen.springbootbasic.controller;

import com.linhnguyen.springbootbasic.dto.UserCreationRequest;
import com.linhnguyen.springbootbasic.dto.UserUpdateRequest;
import com.linhnguyen.springbootbasic.entity.User;
import com.linhnguyen.springbootbasic.service.UserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private UserService userService;
    @PostMapping()
    public User createUser(
            @RequestBody UserCreationRequest req
            ){
        return userService.createUser(req);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest req){
        return userService.updateUser(userId, req);
    }

    @DeleteMapping("/userId")
    public String delete (@PathVariable String userId) {
        return userService.deleteUser(userId);
    }
}
