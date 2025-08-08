package com.linhnguyen.springbootbasic.service;

import com.linhnguyen.springbootbasic.dto.UserCreationRequest;
import com.linhnguyen.springbootbasic.dto.UserUpdateRequest;
import com.linhnguyen.springbootbasic.entity.User;
import com.linhnguyen.springbootbasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest req){
        User newUser = new User();
        newUser.setUsername(req.getUsername());
        newUser.setPassword(req.getPassword());
        newUser.setFirstName(req.getFirstName());
        newUser.setLastName(req.getLastName());
        newUser.setDob(req.getDob());

        return userRepository.save(newUser);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public User updateUser (String userId, UserUpdateRequest req){
        User findUser = userRepository.findById(userId).orElseThrow(() -> null);
        if(findUser == null) {
            return null;
        }
        findUser.setPassword(req.getPassword());
        findUser.setFirstName(req.getFirstName());
        findUser.setLastName(req.getLastName());
        findUser.setDob(req.getDob());
        return userRepository.save(findUser);
    }

    public String deleteUser (String userId) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> null);
        if(findUser == null) {
            return "User not found";
        }

        try{
            userRepository.delete(findUser);
            return "User has been deleted";
        }catch (Exception e){
            System.out.println("Message: " +e.getMessage());
        }
        return null;
    }
}
