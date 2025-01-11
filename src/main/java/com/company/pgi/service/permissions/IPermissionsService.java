package com.company.pgi.service.permissions;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public interface IPermissionsService {

    @ResponseBody
    Boolean ValidPermission(String permisssionCode);
    
}
