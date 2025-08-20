package com.linhnguyen.springbootbasic.controller;

import com.linhnguyen.springbootbasic.dto.ApiResponse;
import com.linhnguyen.springbootbasic.dto.UserCreationRequest;
import com.linhnguyen.springbootbasic.dto.UserUpdateRequest;
import com.linhnguyen.springbootbasic.entity.User;
import com.linhnguyen.springbootbasic.service.UserService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private UserService userService;
    @PostMapping()
    public ApiResponse<User> createUser(
            @RequestBody @Valid UserCreationRequest req
            ){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(req));
        return apiResponse;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public ApiResponse<User> getUser(@PathVariable String userId) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<User> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest req){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, req));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<User> delete (@PathVariable String userId) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
            apiResponse.setMessage(userService.deleteUser(userId));
        return apiResponse;
    }
}
