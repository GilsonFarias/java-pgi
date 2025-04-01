package com.company.pgi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateSchema {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CreateSchema(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Tupla addSquema(String schemaName) {
        try {

            if (!schemaName.matches("^[a-zA-Z0-9_]+$")) {
                throw new IllegalArgumentException("Nome do schema inválido: " + schemaName);
            }

            String sql = "CREATE DATABASE IF NOT EXISTS `" + schemaName
                    + "` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
            jdbcTemplate.execute(sql);
            System.out.println("Schema criado com sucesso: " + schemaName);
            return new Tupla("", true);
        } catch (Exception e) {
            return new Tupla("Error ao tentar criar o schema: " + e.getMessage(), false);
        }
    }
}
