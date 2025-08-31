package com.zoho.hierarchy.api;

import com.zoho.hierarchy.dto.Hierarchy;
import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.dto.UserEntiry;
import com.zoho.hierarchy.repository.HierarchyRepositoryImpl;
import com.zoho.hierarchy.repository.UserRepositoryImpl;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/user/details")
public class GetUserDetails {

    public static UserEntiry getUserDetails(@QueryParam("userId") long userId) throws Exception {
        Optional<User> user = new UserRepositoryImpl().findById(userId);
        Optional<User> reportingTo = new UserRepositoryImpl().findById(user.get().getReportingTo());
        UserEntiry userEntity = new UserEntiry();
        List<String> reportees = new ArrayList<>();

        List<Hierarchy> hierarchy = (List<Hierarchy>) new HierarchyRepositoryImpl().findAllById(userId);

        userEntity.setAge(user.get().getAge());
        userEntity.setName(user.get().getName());
        userEntity.setEmail(user.get().getEmail());
        userEntity.setRole(user.get().getRole());
        userEntity.setPhoneNumber(user.get().getPhoneNumber());
        userEntity.setTeam(user.get().getTeam());
        reportingTo.ifPresent(value -> userEntity.setReportingTo(value.getName()));

        hierarchy.stream().forEach(value -> {
            try {
                Optional<User> optionalUser = new UserRepositoryImpl().findById(userId);
                if (optionalUser.isPresent()) {
                    reportees.add(optionalUser.get().getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        userEntity.setReportees(reportees);

        return userEntity;
    }
}
