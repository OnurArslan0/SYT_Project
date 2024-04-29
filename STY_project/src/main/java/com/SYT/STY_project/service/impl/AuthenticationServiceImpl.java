package com.SYT.STY_project.service.impl;

import com.SYT.STY_project.model.User;
import com.SYT.STY_project.repository.UserRepository;
import com.SYT.STY_project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    public AuthenticationServiceImpl(UserRepository repo){
        this.userRepository = repo;
    }
    @Override
    public boolean authenticateUser(String mail, String password) {
        User user = userRepository.findByMail(mail);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByMail(String mail) {
        return userRepository.getUserByMail(mail);
    }
}
