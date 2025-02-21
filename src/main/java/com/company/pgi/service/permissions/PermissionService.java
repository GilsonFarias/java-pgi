package com.company.pgi.service.permissions;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.pgi.config.SystemPermissions;
import com.company.pgi.exeception.ApiCustomException;
import com.company.pgi.exeception.InternalException;
import com.company.pgi.exeception.PermissionNotFoundException;
import com.company.pgi.model.Permissions;
import com.company.pgi.model.PermissionsProfile;
import com.company.pgi.model.Users;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.repository.IPermissionsProfileRepository;
import com.company.pgi.repository.IPermissionsRepository;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionsProfileRepository iPermissionsProfileRepository;

    @Autowired
    private IPermissionsRepository iPermissionsRepository;

    @Override
    public ResponseBase<Permissions> ListPermissions() {
        ResponseBase<Permissions> responseBase = new ResponseBase<>();

        var permissions = iPermissionsRepository.findAll();
        if(permissions.isEmpty()){
            throw new ApiCustomException(HttpStatus.NOT_ACCEPTABLE,"Resgistro nãos encontrados");
        }

        responseBase.setData(permissions);
        responseBase.setStatusCode(HttpStatus.OK);
        responseBase.setMessage("Ok");

        return responseBase;
    }

    @Override
    @Transactional
    public void ValidPermission(String permisssionCode) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Boolean permissionAccess = false;

            if ( authentication != null && authentication.isAuthenticated()) {
                Users user = (Users) authentication.getPrincipal();

                List<PermissionsProfile> permissions = iPermissionsProfileRepository.findByUserProfile(user.getUserProfile());

                for (PermissionsProfile permission : permissions) {
                    if (Objects.equals(permission.getPermissions().getkeyCode(), permisssionCode)) {
                        if (permission.isStatus()) {
                            permissionAccess = true;
                        }
                    }
                }
            }

            if(!permissionAccess)
                throw new PermissionNotFoundException();

            //return permit;

        } catch (InternalException e) {
            throw new PermissionNotFoundException();
        }
    }

    @Override
    @Transactional
    public Boolean PermissionExternal(String permissionCode){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Boolean permissionAccess = false;
            
            if ( authentication != null && authentication.isAuthenticated()) {
                Users user = (Users) authentication.getPrincipal();
                
                List<PermissionsProfile> permissions = iPermissionsProfileRepository.findByUserProfile(user.getUserProfile());
                
                for (PermissionsProfile permission : permissions) {
                    if (Objects.equals(permission.getPermissions().getkeyCode(), permissionCode)) {
                        if (permission.isStatus()) {
                            permissionAccess = true;
                        }
                    }
                }
            }
            
            return permissionAccess;
        } catch (Exception e) {
            throw new PermissionNotFoundException();
        }
    }

    @Override
    public ResponseBase<Permissions> updatePermissionsList() {
        ResponseBase<Permissions> responseBase = new ResponseBase<>();

        iPermissionsRepository.saveAll(SystemPermissions.getSystemPermissions());

        return responseBase;

    }

    @Override
    public ResponseBase<Permissions> updateProfiles(){
        ResponseBase<Permissions> responseBase = new ResponseBase<>();

        iPermissionsProfileRepository.saveAll(SystemPermissions.getSystemPermissionsProfile());

        return responseBase;
    }
    
}

