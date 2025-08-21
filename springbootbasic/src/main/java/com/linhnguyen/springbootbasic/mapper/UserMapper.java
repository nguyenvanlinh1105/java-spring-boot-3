package com.linhnguyen.springbootbasic.mapper;

import com.linhnguyen.springbootbasic.dto.request.UserCreationRequest;
import com.linhnguyen.springbootbasic.dto.request.UserUpdateRequest;
import com.linhnguyen.springbootbasic.dto.response.UserResponse;
import com.linhnguyen.springbootbasic.entity.User;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest req);
    UserResponse toUserResponse (User user);
    List<UserResponse> toListUserResponse (List<User> users);
    void updateUser(@MappingTarget User user, UserUpdateRequest req);
}
