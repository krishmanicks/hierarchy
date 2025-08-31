package com.zoho.hierarchy.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoho.hierarchy.dto.ApprovalRequest;
import com.zoho.hierarchy.dto.Hierarchy;
import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.repository.ApprovalRequestRepositoryImpl;
import com.zoho.hierarchy.repository.HierarchyRepositoryImpl;
import com.zoho.hierarchy.repository.UserRepositoryImpl;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Optional;

@Path("/update")
public class UpdateUser {

    private final HierarchyRepositoryImpl hierarchyRepository = new HierarchyRepositoryImpl();
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private final ApprovalRequestRepositoryImpl approvalRequestRepository = new ApprovalRequestRepositoryImpl();

    @POST
    public void updateUser(@QueryParam("userId") long userId, User user) throws Exception {

        Optional<User> optionalUser = userRepository.findById(userId);

        optionalUser.get().setAge(user.getAge());
        optionalUser.get().setName(user.getName());
        optionalUser.get().setPhoneNumber(user.getPhoneNumber());
        optionalUser.get().setEmail(user.getEmail());

        Optional<Hierarchy> nextUser = hierarchyRepository.nextHierarchy(user.getId());
        if (nextUser.isEmpty()) {
            new UserRepositoryImpl().update(user);
            return;
        }

        String updatedJson = new ObjectMapper().writeValueAsString(optionalUser);

        optionalUser.get().setUpdatedJson(updatedJson);
        optionalUser.get().setApprovalNeeded(true);

        ApprovalRequest approvalRequest = new ApprovalRequest();
        approvalRequest.setApprovalId(nextUser.get().getId());
        approvalRequest.setUserId(userId);

        approvalRequestRepository.save(approvalRequest);
        userRepository.update(optionalUser.get());
    }
}
