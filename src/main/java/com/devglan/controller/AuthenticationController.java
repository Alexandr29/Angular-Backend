package com.devglan.controller;

import com.devglan.config.JwtTokenUtil;
import com.devglan.model.*;
import com.devglan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        List<User> users = userService.findAll();
        for (User u: users) {
            System.out.println(u.toString());
        }
        System.out.println(userService.findOne(loginUser.getUsername()).toString());

        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        final String token = "token";
        if (user.getRoleId()==1L){
            return new ApiResponse<>(200, "admin",new AuthToken(token, user.getLogin()));
        }else if(user.getRoleId()==2L){
            return new ApiResponse<>(200, "user",new AuthToken(token, user.getLogin()));
        } return new ApiResponse<>(200, "anon",null);

}

}
