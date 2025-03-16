package com.company.pgi.service.permissions;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.pgi.model.Permissions;
import com.company.pgi.model.dto.ResponseBase;

@Service
public interface IPermissionService {

    @ResponseBody
    Boolean ValidPermission(String permisssionCode);

    ResponseBase<Permissions> updatePermissionsList();

    ResponseBase<Permissions> ListPermissions();

    ResponseBase<Permissions> updateProfiles();

}
