package com.SYT.STY_project.service.impl;

import com.SYT.STY_project.dto.UserDTO;
import com.SYT.STY_project.model.User;
import com.SYT.STY_project.repository.UserRepository;
import com.SYT.STY_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository repo){
        this.userRepository = repo;
    }
    @Override
    public UserDTO getUserById(String userId) {
        return null;
    }

    private User createUserFromDto(UserDTO userDto) {
        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setMail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
    public String createUser(UserDTO user) {
        if (userRepository.findByMail(user.getEmail()) != null) {
            throw new RuntimeException("Bu e-posta adresi zaten kullanımda.");
        }
        if (userRepository.findByUserName(user.getUsername()) != null) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanımda.");
        }

        return userRepository.save(createUserFromDto(user)).getMail();
    }
}
