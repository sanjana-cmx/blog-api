package com.app.blog.service;

import com.app.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);

    UserDto getUserById(Integer userId);

   List<UserDto> getAllUsers();
   void deleteUser(Integer userId);

}
