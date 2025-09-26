package com.company.pgi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.company.pgi.model.dto.AddSchemaDto;
import com.company.pgi.service.permissions.IPermissionService;

@Component
public class CreateSchema {

    @Autowired
    private IPermissionService iPermissionService;

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public CreateSchema(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Tupla addSquema(AddSchemaDto schemaDt) {
        iPermissionService.ValidPermission("MOD100");
        try {

            if( schemaDt.getModule().isEmpty() || schemaDt.getModule().isEmpty()) {
                throw new IllegalArgumentException("Error: Dados inválido: ");
            }

            String module = schemaDt.getModule();
            String cnpj = schemaDt.getCnpj();
            String schemaName = module + "_" + cnpj;

            if (!schemaName.matches("^[a-z0-9_]+$")) {
                throw new IllegalArgumentException("Error: Nome do schema inválido: " + schemaName);
            }

            String sql = "CREATE DATABASE " + schemaName + " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
            jdbcTemplate.execute(sql);
            System.out.println("Schema criado com sucesso: " + schemaName);
            return new Tupla("Schema criado com socesso", true);
        } catch (Exception e) {
            String typeMsg = e.getMessage().substring(0, 6);
            if( typeMsg.equals("Error:")) {
                return new Tupla( e.getMessage(), false);
            } else {
                return new Tupla("Error interno ao tentar criar o schema: ", false);
            }
        }
    }
}
