package com.zoho.hierarchy.hr;

import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.enums.Roles;
import com.zoho.hierarchy.repository.HrRepositoryImpl;
import com.zoho.hierarchy.repository.UserRepositoryImpl;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RolesAllowed({Roles.HR})
@Path("hr/approvals")
public class ApproveProfile {

    private final HrRepositoryImpl hrRepository = new HrRepositoryImpl();
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @GET
    @Path("/pending")
    private List<User> getPendingApprovals() throws Exception {

        List<HrApprovalRequest> hrApprovalRequestList = (List<HrApprovalRequest>) hrRepository.findAll();

        if (hrApprovalRequestList.isEmpty()) {
            return new ArrayList<>();
        }

        List<User> users = new ArrayList<>();
        for (HrApprovalRequest hrApprovalRequest : hrApprovalRequestList) {
            Optional<User> user = userRepository.findById(hrApprovalRequest.getUserId());
            user.ifPresent(users::add);
        }

        return users;

    }

    @GET
    @Path("/approve")
    private void acceptRequest(@QueryParam("userId") long userId) throws Exception {
        hrRepository.deleteByUserId(userId);

    }


}
