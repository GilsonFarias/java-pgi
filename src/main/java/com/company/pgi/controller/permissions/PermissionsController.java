package com.company.pgi.controller.permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Permissions;
import com.company.pgi.model.dto.ResponseBase;
import com.company.pgi.service.permissions.IPermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionsController {

    @Autowired
    private IPermissionService iPermissionService;

    @GetMapping("/atualizar")
    public String updatePermissionsList() {

        iPermissionService.updatePermissionsList();

        return "Permissões atualizadas";
    }

    @PostMapping("/atualizarProfiles/{idProfile}")
    public String updateProfiles(@PathVariable Long idProfile) {

        iPermissionService.updateProfiles(idProfile);

        return "Permissões por perfil atualizadas";
    }

    @GetMapping("/list")
    public ResponseBase<Permissions> ListPermissions() {

        return iPermissionService.ListPermissions();
    }

    @GetMapping("/permission")
    public ResponseEntity<Boolean> permisions(@RequestHeader("Authorization") String token,
            @RequestHeader(name = "Permission", required = false) String permission) {

        var rPermission = iPermissionService.ValidPermission(permission);

        return ResponseEntity.ok(rPermission);
    }
}
