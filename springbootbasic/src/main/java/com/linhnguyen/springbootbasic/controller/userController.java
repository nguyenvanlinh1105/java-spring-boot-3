package com.linhnguyen.springbootbasic.controller;

import com.linhnguyen.springbootbasic.dto.response.ApiResponse;
import com.linhnguyen.springbootbasic.dto.request.UserCreationRequest;
import com.linhnguyen.springbootbasic.dto.request.UserUpdateRequest;
import com.linhnguyen.springbootbasic.dto.response.UserResponse;
import com.linhnguyen.springbootbasic.entity.User;
import com.linhnguyen.springbootbasic.service.UserService;
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
    public ApiResponse<List<UserResponse>> getUsers() {
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest req){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
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
