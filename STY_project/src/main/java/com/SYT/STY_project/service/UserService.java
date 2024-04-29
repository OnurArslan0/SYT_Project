package com.SYT.STY_project.service;

import com.SYT.STY_project.dto.UserDTO;

public interface UserService {

    UserDTO getUserById(String userId);

    String createUser(UserDTO userDto);

}
