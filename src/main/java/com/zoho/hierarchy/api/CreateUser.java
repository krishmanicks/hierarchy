package com.zoho.hierarchy.api;

import com.zoho.hierarchy.Auth.JwtUtil;
import com.zoho.hierarchy.Auth.LoginResponse;
import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.enums.Roles;
import com.zoho.hierarchy.hr.HrApprovalRequest;
import com.zoho.hierarchy.repository.HrRepositoryImpl;
import com.zoho.hierarchy.repository.UserRepositoryImpl;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.logging.Logger;

@Path("/api/create")
public class CreateUser {

    @PUT
    public LoginResponse createUser() {
        User newUser = new User();

        Logger.getLogger("2345678").info("successfully reached the server");
//        newUser.setName(user.getName());
//        newUser.setAge(user.getAge());
//        newUser.setEmail(user.getEmail());
//        newUser.setPhoneNumber(user.getPhoneNumber());
//        newUser.setRole(user.getRole());
//
//        if (user.getRole().equals(Roles.HR)) {
//            newUser.setTeam("Human Resources");
//            newUser.setHrApproval(false);
//        }
//
//        try {
//            User createdUser = new UserRepositoryImpl().save(newUser);
//            updateForHr(createdUser);
//            String token = JwtUtil.generateToken(user.getName(), user.getRole());
//            return new LoginResponse(user.getName(), user.getRole(), token);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return new LoginResponse("5gfcsda", "jsgdjh", "jagdjsad");
    }

    private void updateForHr(User user) throws Exception {
        HrApprovalRequest request = new HrApprovalRequest();

        request.setUserId(user.getId());
        new HrRepositoryImpl().save(request);
    }

}
