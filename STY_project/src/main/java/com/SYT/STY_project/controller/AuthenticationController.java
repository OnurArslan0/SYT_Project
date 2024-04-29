package com.SYT.STY_project.controller;
import com.SYT.STY_project.dto.LoginDTO;
import com.SYT.STY_project.dto.TokenDTO;
import com.SYT.STY_project.model.User;
import com.SYT.STY_project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.SYT.STY_project.util.TokenUtil;
@RestController
@RequestMapping(path = "/aut")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    TokenUtil tokenUtil;

    @GetMapping("/{mail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String mail) {
        User user = authenticationService.getUserByMail(mail);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<TokenDTO> loginUser(@RequestBody LoginDTO loginDto) {
        boolean isAuthenticated = authenticationService.authenticateUser(loginDto.getMail(), loginDto.getPassword());

        if (isAuthenticated) {
            String token = tokenUtil.generateToken(loginDto.getMail());
            TokenDTO tokenDto = new TokenDTO(token);
            System.out.println( loginDto.getMail()+" mail adresiyle kayıt yapan üye giriş yaptı. \nTokeni: "+tokenDto.getToken());
            return ResponseEntity.ok(tokenDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

