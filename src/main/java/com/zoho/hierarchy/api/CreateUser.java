package com.zoho.hierarchy.api;

import com.zoho.hierarchy.Auth.JwtUtil;
import com.zoho.hierarchy.Auth.LoginResponse;
import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.enums.Roles;
import com.zoho.hierarchy.hr.HrApprovalRequest;
import com.zoho.hierarchy.internal.Beans;
import com.zoho.hierarchy.repository.HrRepositoryImpl;
import com.zoho.hierarchy.repository.UserRepository;
import com.zoho.hierarchy.repository.UserRepositoryImpl;


import org.glassfish.jersey.process.internal.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Path("/api/create")
public class CreateUser {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> listZServices() {
        List<String> services = new ArrayList<>();
        services.add("I am batman"); //No I18N
        services.add("I am Iron Man"); //No I18N
        services.add("Zoho Hierarchy"); //No I18N

        try {
            Logger.getLogger("2345678").info("successfully reached the server");
            User newUser = new User();
            newUser.setName("krish");
            newUser.setAge(24);
            newUser.setEmail("krish@zohocorp.com");
            newUser.setPhoneNumber(1234567890);
            newUser.setRole(Roles.USER);
                        User createdUser = new UserRepositoryImpl().save(newUser);
            Logger.getLogger("2345678").info("successfully saved in db");
        } catch (Exception e) {
            Logger.getLogger("2345678").info("error occurred" + e);
            services.add(e.getMessage());
            services.add(e.getLocalizedMessage());
            services.add(String.valueOf(e.getCause()));
            services.add(String.valueOf(e));

        }

        return services;
    }

    private UserRepository getRepository()  {
        return Beans.lookup(Beans.USER_REPOSITORY);
    }
//    @GET
//    public LoginResponse createUser() {
//        try {
//            Logger.getLogger("2345678").info("successfully reached the server");
//            User newUser = new User();
//            newUser.setName("krish");
//            newUser.setAge(24);
//            newUser.setEmail("krish@zohocorp.com");
//            newUser.setPhoneNumber(1234567890);
//            newUser.setRole(Roles.USER);
//                        User createdUser = new UserRepositoryImpl().save(newUser);
//            Logger.getLogger("2345678").info("successfully saved in db");
//        } catch (Exception e) {
//            Logger.getLogger("2345678").info("error occurred" + e);
//
//        }
////        newUser.setName(user.getName());
////        newUser.setAge(user.getAge());
////        newUser.setEmail(user.getEmail());
////        newUser.setPhoneNumber(user.getPhoneNumber());
////        newUser.setRole(user.getRole());
////
////        if (user.getRole().equals(Roles.HR)) {
////            newUser.setTeam("Human Resources");
////            newUser.setHrApproval(false);
////        }
////
////        try {
////            User createdUser = new UserRepositoryImpl().save(newUser);
////            updateForHr(createdUser);
////            String token = JwtUtil.generateToken(user.getName(), user.getRole());
////            return new LoginResponse(user.getName(), user.getRole(), token);
////        } catch (Exception e) {
////            throw new RuntimeException(e);
////        }
//        return new LoginResponse("5gfcsda", "jsgdjh", "jagdjsad");
//    }
//
//    private void updateForHr(User user) throws Exception {
//        HrApprovalRequest request = new HrApprovalRequest();
//
//        request.setUserId(user.getId());
//        new HrRepositoryImpl().save(request);
//    }

}
