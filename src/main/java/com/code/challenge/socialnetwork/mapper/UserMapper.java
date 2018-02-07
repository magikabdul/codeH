package com.code.challenge.socialnetwork.mapper;

import com.code.challenge.socialnetwork.domain.User;
import com.code.challenge.socialnetwork.domain.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getNick());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getFirstName(),
                user.getLastName(),
                user.getNick());
    }
}
