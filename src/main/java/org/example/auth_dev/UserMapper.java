package org.example.auth_dev;

import com.goit.exception.LogEnum;
import com.goit.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Slf4j
@Component
public class UserMapper {
    public static UserDto toDTO(User user) {
        log.info(String.format("%s User entity (id: %s) was mapped to UserDto", LogEnum.MAPPER, user.getId()));
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles()).build();
    }

    public static User toEntity(UserDto userDTO) {
        log.info(String.format("%s UserDto (id: %s) was mapped to User entity", LogEnum.MAPPER, userDTO.getId()));
        return new User(userDTO.getEmail(), userDTO.getPassword());
    }

    public UserResponse toUserResponse(UserDto userDto) {
        log.info(String.format("%s UserDto (id: %s) was mapped to UserResponse entity", LogEnum.MAPPER, userDto.getId()));
        return UserResponse.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .roles(new HashSet<>(userDto.getRoles())).build();
    }
}