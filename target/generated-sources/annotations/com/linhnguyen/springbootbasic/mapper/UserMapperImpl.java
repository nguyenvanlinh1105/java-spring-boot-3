package com.linhnguyen.springbootbasic.mapper;

import com.linhnguyen.springbootbasic.dto.request.UserCreationRequest;
import com.linhnguyen.springbootbasic.dto.request.UserUpdateRequest;
import com.linhnguyen.springbootbasic.dto.response.UserResponse;
import com.linhnguyen.springbootbasic.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-21T21:56:41+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest req) {
        if ( req == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( req.getUsername() );
        user.setPassword( req.getPassword() );
        user.setFirstName( req.getFirstName() );
        user.setLastName( req.getLastName() );
        user.setDob( req.getDob() );

        return user;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.dob( user.getDob() );

        return userResponse.build();
    }

    @Override
    public List<UserResponse> toListUserResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( toUserResponse( user ) );
        }

        return list;
    }

    @Override
    public void updateUser(User user, UserUpdateRequest req) {
        if ( req == null ) {
            return;
        }

        user.setPassword( req.getPassword() );
        user.setFirstName( req.getFirstName() );
        user.setLastName( req.getLastName() );
        user.setDob( req.getDob() );
    }
}
