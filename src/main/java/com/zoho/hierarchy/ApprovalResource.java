package com.zoho.hierarchy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoho.hierarchy.dto.ApprovalRequest;
import com.zoho.hierarchy.dto.Hierarchy;
import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.enums.Roles;
import com.zoho.hierarchy.repository.ApprovalRequestRepositoryImpl;
import com.zoho.hierarchy.repository.HierarchyRepositoryImpl;
import com.zoho.hierarchy.repository.UserRepositoryImpl;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

@Path("/request")
@RolesAllowed({Roles.MENTOR, Roles.TEAMLEAD, Roles.MANAGER})
public class ApprovalResource {

    private final ApprovalRequestRepositoryImpl approvalRequestRepository = new ApprovalRequestRepositoryImpl();
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @GET
    @Path("/pending")
    public List<ApprovalRequest> getPendingRequests(@QueryParam("userId") long userId) {
        return (List<ApprovalRequest>) approvalRequestRepository.findAllById(userId);
    }

    @POST
    @Path("/approve")
    public void approveRequests(@QueryParam("currentTaskId") long currentTaskId) throws Exception {

        Optional<ApprovalRequest> approveRequest = approvalRequestRepository.findById(currentTaskId);
        if (approveRequest.isPresent()) {
            long reportingToID = approveRequest.get().getApprovalId();

            Optional<Hierarchy> nextHierarchy = new HierarchyRepositoryImpl().nextHierarchy(reportingToID);
            if (nextHierarchy.isEmpty()) {
                long currentUser = approveRequest.get().getUserId();
                Optional<User> user = userRepository.findById(currentUser);
                String updatedJson = user.get().getUpdatedJson();

                User newUser = new ObjectMapper().readValue(updatedJson, User.class);
                newUser.setApprovalNeeded(false);
                newUser.setUpdatedJson("");

                userRepository.update(newUser);
                new ApprovalRequestRepositoryImpl().deleteById(currentTaskId);
            } else {
                approveRequest.get().setApprovalId(nextHierarchy.get().getReportingTo());
                approvalRequestRepository.update(approveRequest.get());
            }

        }

    }
}
