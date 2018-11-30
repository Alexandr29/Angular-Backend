package com.nixsolutions.controller;

import com.nixsolutions.model.*;
import com.nixsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        final String token = "token";
        if (user.getRoleId() == 1L) {
            return new ApiResponse<>(200, "admin", new AuthToken(token, user.getLogin()));
        } else if (user.getRoleId() == 2L) {
            return new ApiResponse<>(200, "user", new AuthToken(token, user.getLogin()));
        }
        return new ApiResponse<>(200, "anon", null);

    }

}
