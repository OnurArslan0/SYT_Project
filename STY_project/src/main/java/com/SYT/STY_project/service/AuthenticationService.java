package com.SYT.STY_project.service;

import com.SYT.STY_project.model.User;

public interface AuthenticationService {
    boolean authenticateUser(String username, String password);

    User getUserByMail(String mail);
}
