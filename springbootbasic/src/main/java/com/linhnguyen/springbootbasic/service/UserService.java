package com.linhnguyen.springbootbasic.service;

import com.linhnguyen.springbootbasic.dto.request.UserCreationRequest;
import com.linhnguyen.springbootbasic.dto.request.UserUpdateRequest;
import com.linhnguyen.springbootbasic.dto.response.UserResponse;
import com.linhnguyen.springbootbasic.entity.User;
import com.linhnguyen.springbootbasic.exception.AppException;
import com.linhnguyen.springbootbasic.exception.ErrorCode;
import com.linhnguyen.springbootbasic.mapper.UserMapper;
import com.linhnguyen.springbootbasic.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    // sử dụng annotation của lombok bestpractise hơn autowired
    UserRepository userRepository;

    //sử dụng autowired
    @Autowired
    private UserMapper userMapper;


    public User createUser(UserCreationRequest req){

        if(userRepository.existsByUsername(req.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User newUser = userMapper.toUser(req);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public List<UserResponse> getUsers(){
        return userMapper.toListUserResponse(userRepository.findAll());
    }

    public UserResponse getUser(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUD)));
    }

    public UserResponse updateUser (String userId, UserUpdateRequest req){
        User findUser = userRepository.findById(userId).orElseThrow(() -> null);
        if(findUser == null) {
            return null;
        }
        userMapper.updateUser(findUser, req);
        return userMapper.toUserResponse(userRepository.save(findUser));
    }

    @Transactional
    public String deleteUser(String userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUD));
        userRepository.delete(findUser);
        return "User has been deleted";
    }
}
