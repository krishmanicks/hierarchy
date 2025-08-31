package com.zoho.hierarchy.Auth;

import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.repository.UserRepositoryImpl;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Optional;

@Path("/login")
public class Login {

    public LoginResponse login(@QueryParam("name") String name) throws Exception {
        Optional<User> user = new UserRepositoryImpl().findByName(name);

        if (user.isEmpty()) {
            throw new RuntimeException(String.format("no user found by the name %s", name));
        }
        String token = JwtUtil.generateToken(user.get().getName(), user.get().getRole());

        return new LoginResponse(user.get().getName(), user.get().getRole(), token);
    }
}
