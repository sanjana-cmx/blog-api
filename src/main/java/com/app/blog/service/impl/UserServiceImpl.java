package com.app.blog.service.impl;

import com.app.blog.entity.User;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.UserDto;
import com.app.blog.repository.UserRepo;
import com.app.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToEntity(userDto);
        User savedUser = this.userRepo.save(user);
        return this.entityToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
         return this.entityToDto(updatedUser);


    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users= this.userRepo.findAll();
        List<UserDto> userDtoList = users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);
    }

    private User dtoToEntity(UserDto userDto){
        //Manual way to convert object
        /*User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());*/

        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }

    private UserDto entityToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        /*userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());*/
        return userDto;
    }
}
