package com.SYT.STY_project.controller;

import com.SYT.STY_project.dto.UserDTO;
import com.SYT.STY_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/{userId}")
    public UserDTO getUserById(@PathVariable("userId")String userId){
        return userService.getUserById(userId);
    }

    @PostMapping(path = "/reg")
    public String createUserRegister(@RequestBody Map<String, String> requestParams) {
        String userName = requestParams.get("username");
        String mail = requestParams.get("mail");
        String password = requestParams.get("password");

        if (userName == null || userName.isEmpty() || mail == null || mail.isEmpty() || password == null || password.isEmpty()) {
            return "There are empty fields. Please fill in all fields.";
        }

        UserDTO userDTO = new UserDTO(userName, mail, password);
        return userService.createUser(userDTO)+" kaydedildi";
    }
}
