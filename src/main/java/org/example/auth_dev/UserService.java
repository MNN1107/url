package org.example.auth_dev;


import java.util.List;

public interface UserService {

    UserDto createUser(String email, String rawPassword) throws UserAlreadyExistException;
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    User getByEmail(String email) throws UserNotFoundException;
    UserDto updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
    boolean existsByEmail(String email);
}
