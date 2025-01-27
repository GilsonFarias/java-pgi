package com.company.pgi.controller.permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Permissions;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.service.permissions.IPermissionsService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionsController {

    @Autowired
    private IPermissionsService iPermissionsService;

    @GetMapping("/atualizar")
    public String updatePermissionsList() {
        
        iPermissionsService.updatePermissionsList();

        return "";
    }

    @GetMapping("/atualizarProfiles")
    public String updateProfiles() {
        
        iPermissionsService.updateProfiles();

        return "";
    }

    
    @GetMapping("list")
    public ResponseBase<Permissions> ListPermisssions() {

        return iPermissionsService.ListPermisssions();
    }
}
