package com.company.pgi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.company.pgi.model.Permissions;
import com.company.pgi.model.PermissionsProfile;
import com.company.pgi.model.Profile;

@Component
public class SystemPermissions {

    public static List<Permissions> getSystemPermissions() {
        List<Permissions> permissions = new ArrayList<>();
        // Penson
        permissions.add(new Permissions("PEN101", "AMD", "Incluir pessoa", "Incluir pessoa"));
        permissions.add(new Permissions("PEN102", "AMD", "Editar pessoa", "Editar pessoa"));
        permissions.add(new Permissions("PEN103", "AMD", "Deletar pessoa", "Deletar pessoa"));
        permissions.add(new Permissions("PEN104", "AMD", "Visualizar Pessoa", "Visualizar pessoa"));
        permissions.add(new Permissions("PEN105", "AMD", "Listar pessoas", "Listar pessoas"));
        // User
        permissions.add(new Permissions("USE101", "ADM", "Incluir usuário", "Incluir usuário"));
        permissions.add(new Permissions("USE102", "ADM", "Editar usuário", "Editar usuário"));
        permissions.add(new Permissions("USE103", "ADM", "Deletar usuário", "Deletar usuário"));
        permissions.add(new Permissions("USE104", "ADM", "Visualizar usuário", "Visualizar usuário"));
        permissions.add(new Permissions("USE105", "ADM", "Listar usuários", "Listar usuários"));
        permissions.add(new Permissions("USE106", "ADM", "Editar senha", "Editar senha"));
        // Permission
        permissions.add(new Permissions("PER101", "ADM", "Incluir permissão", "Incluir permissão"));
        permissions.add(new Permissions("PER102", "ADM", "Editar permissão", "Editar permissão"));
        permissions.add(new Permissions("PER103", "ADM", "Deletar permissão", "Deletar permissão"));
        permissions.add(new Permissions("PER104", "ADM", "Visualizar permissão", "Visualizar permissão"));
        permissions.add(new Permissions("PER105", "ADM", "Atualizar permissão", "Atualizar permissão"));
        // Perfil do usuário
        permissions.add(new Permissions("UPF101", "ADM", "Incluir perfil", "Incluir perfil"));
        permissions.add(new Permissions("UPF102", "ADM", "Editar perfil", "Editar perfil"));
        permissions.add(new Permissions("UPF103", "ADM", "Deletar perfil", "Deletar perfil"));
        permissions.add(new Permissions("UPF104", "ADM", "Visualizar perfil", "Visualizar perfil"));
        permissions.add(new Permissions("UPF105", "ADM", "Atualizar perfil", "Atualizar perfil"));
        // Products / STOCK
        permissions.add(new Permissions("PRO101", "STOCK", "Incluir produto", "Incluir produto"));
        permissions.add(new Permissions("PRO102", "STOCK", "Editar produto", "Editar produto"));
        permissions.add(new Permissions("PRO103", "STOCK", "Deletar produto", "Deletar produto"));
        permissions.add(new Permissions("PRO104", "STOCK", "Visualizar produto", "Visualizar produto"));
        permissions.add(new Permissions("PRO105", "STOCK", "Listar produto", "Listar produto"));

        return permissions;
    }

    public static List<PermissionsProfile> getSystemPermissionsProfile() {

        List<PermissionsProfile> permissionsProfiles = new ArrayList<>();

        Profile uProfile = new Profile();

        uProfile.setId(1L);

        var permissions = getSystemPermissions();

        for (Permissions permission : permissions) {
            permissionsProfiles.add(new PermissionsProfile(null, permission, uProfile, true));
        }

        return permissionsProfiles;
    }

}
