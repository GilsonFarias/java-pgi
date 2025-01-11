package com.company.pgi.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.company.pgi.model.Permissions;

@Component
public class SystemPermissions {
    public static List<Permissions> getSystemPermissions(){
        List<Permissions> permissions = new ArrayList<>();

        permissions.add(new Permissions("PEN101","Incluir Pessoa", "Incluir Pessoa"));
        permissions.add(new Permissions("PEN102","Editar Pessoa", "Editar Pessoa"));
        permissions.add(new Permissions("PEN103","Deletar Pessoa", "Deletar Pessoa"));
        permissions.add(new Permissions("PEN104","Visualizar Pessoa", "Visualizar Usuário"));

        permissions.add(new Permissions("USE101","Incluir Usuário", "Incluir Pessoa"));
        permissions.add(new Permissions("USE102","Editar Usuário", "Editar Pessoa"));
        permissions.add(new Permissions("USE103","Deletar User", "Deletar Pessoa"));
        permissions.add(new Permissions("USE104","Visualizar User", "Visualizar Usuário"));

        permissions.add(new Permissions("PRO101","Incluir Usuário", "Incluir Pessoa"));
        permissions.add(new Permissions("PRO102","Editar Usuário", "Editar Pessoa"));
        permissions.add(new Permissions("PRO103","Deletar User", "Deletar Pessoa"));
        permissions.add(new Permissions("PRO104","Visualizar User", "Visualizar Usuário"));

        return permissions;
    }

}
