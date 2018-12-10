package com.nixsolutions.webservice;

import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.impl.UsernamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/temp")
public class RestControllerLogin {
    private final String messageAdmin = new String("{\"message\":\"admin\"}");
    private final String messageUser = new String("{\"message\":\"user\"}");
    private final String messageWrong = new String("{\"message\":\"invalid Credentials\"}");

    @Autowired
    UserService userService;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response postUser(UsernamePassword credentials) {
        String login = credentials.getUsername();
        User user = userService.findByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(credentials.getPassword())) {
                if (user.getRoleId() == 1L) {
                    return Response
                            .status(Response.Status.OK)
                            .entity(messageAdmin)
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                } else {
                    return Response
                            .status(Response.Status.OK)
                            .entity(messageUser)
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                }
            }
        }
        return Response
                .status(Response.Status.OK)
                .entity(messageWrong)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
