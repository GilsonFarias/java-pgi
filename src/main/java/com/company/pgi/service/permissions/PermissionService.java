package com.company.pgi.service.permissions;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.pgi.exeception.UserNotFoundException;
import com.company.pgi.model.PermissionsProfile;
import com.company.pgi.model.Users;
import com.company.pgi.repository.IPermissionsProfileRepository;

@Service
public class PermissionService implements IPermissionsService {
    @Autowired
    private IPermissionsProfileRepository iPermissionsProfileRepository;

    @Override
    @Transactional
    public Boolean ValidPermission(String permisssionCode) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                Users user = (Users) authentication.getPrincipal();

                List<PermissionsProfile> permissions = iPermissionsProfileRepository
                        .findByUserProfile(user.getUserProfile());

                for (PermissionsProfile permission : permissions) {
                    if (Objects.equals(permission.getPermissions().getkeyCode(), permisssionCode)
                            && permission.isStatus()) {
                        return true;
                    }
                }
            }
            return false;

        } catch (Exception e) {
            throw new UserNotFoundException("Error em PermissionService.ValidPermission: " + e.getMessage());
        }
    }
}

