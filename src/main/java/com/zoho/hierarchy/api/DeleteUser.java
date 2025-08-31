package com.zoho.hierarchy.api;

import com.zoho.hierarchy.dto.Hierarchy;
import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.enums.Roles;
import com.zoho.hierarchy.repository.HierarchyRepositoryImpl;
import com.zoho.hierarchy.repository.UserRepositoryImpl;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Collection;
import java.util.Optional;

@RolesAllowed(Roles.HR)
@Path("/delete")
public class DeleteUser {

    private final HierarchyRepositoryImpl hierarchyRepository = new HierarchyRepositoryImpl();

    @DELETE
    public void deleteUser(@QueryParam("userId") long userId) throws Exception {
        Optional<User> user = new UserRepositoryImpl().findById(userId);

        if (user.get().getRole().equals(Roles.HR)) {
            new UserRepositoryImpl().deleteById(userId);
            return;
        }

        Optional<Hierarchy> reportingUser = new HierarchyRepositoryImpl().findByUserId(userId);
        Collection<Hierarchy> reportees = hierarchyRepository.findAllById(userId);

        if (user.get().getRole().equals(Roles.USER)) {
            hierarchyRepository.deleteById(userId);
        } else if (user.get().getRole().equals(Roles.MANAGER)) {
            if (reportingUser.isEmpty()) {
                if (!reportees.isEmpty()) {
                    reportees.stream().forEach(key -> {
                        try {
                            hierarchyRepository.deleteById(key.getId());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        } else {
            if (reportingUser.isPresent()) {
                if (!reportees.isEmpty()) {
                    reportees.stream().forEach(key -> {
                        try {
                            key.setReportingTo(reportingUser.get().getId());
                            hierarchyRepository.update(key);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        }
            new UserRepositoryImpl().deleteById(userId);
    }
}
