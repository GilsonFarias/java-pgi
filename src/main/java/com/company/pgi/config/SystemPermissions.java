package com.company.pgi.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.company.pgi.model.Permissions;
import com.company.pgi.model.PermissionsProfile;
import com.company.pgi.model.UserProfile;

@Component
public class SystemPermissions {
    
    public static List<Permissions> getSystemPermissions(){
        List<Permissions> permissions = new ArrayList<>();
        // Penson
        permissions.add(new Permissions("PEN101","Incluir pessoa", "Incluir pessoa"));
        permissions.add(new Permissions("PEN102","Editar pessoa", "Editar pessoa"));
        permissions.add(new Permissions("PEN103","Deletar pessoa", "Deletar pessoa"));
        permissions.add(new Permissions("PEN104","Visualizar Pessoa", "Visualizar pessoa"));
        permissions.add(new Permissions("PEN105","Listar pessoas", "Listar pessoas"));
        // User
        permissions.add(new Permissions("USE101","Incluir usuário", "Incluir usuário"));
        permissions.add(new Permissions("USE102","Editar usuário", "Editar usuário"));
        permissions.add(new Permissions("USE103","Deletar usuário", "Deletar usuário"));
        permissions.add(new Permissions("USE104","Visualizar usuário", "Visualizar usuário"));
        permissions.add(new Permissions("USE105","Listar usuários", "Listar usuários"));
        // Permission
        permissions.add(new Permissions("PER101","Incluir permissão", "Incluir permissão"));
        permissions.add(new Permissions("PER102","Editar permissão", "Editar permissão"));
        permissions.add(new Permissions("PER103","Deletar permissão", "Deletar permissão"));
        permissions.add(new Permissions("PER104","Visualizar permissão", "Visualizar permissão"));
        permissions.add(new Permissions("PER105","Atualizar permissão", "Atualizar permissão"));

        return permissions;
    }

    public static List<PermissionsProfile> getSystemPermissionsProfile(){

        List<PermissionsProfile> permissionsProfiles = new ArrayList<>();

        UserProfile uProfile = new UserProfile();
        
        uProfile.setId(1L);

        var permissions = getSystemPermissions();

        for (Permissions permission : permissions) {
            permissionsProfiles.add(new PermissionsProfile( null, permission, uProfile, true));
        }

        return permissionsProfiles;
    }

}
