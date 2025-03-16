package com.company.pgi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.HttpStatus;

import com.company.pgi.exeception.ApiCustomException;

public class DatabaseCreateUtils {

    public void MontaConexao(String url, String user, String password, String newSchema) {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            createSchema(connection, newSchema);
        } catch (SQLException e) {
            throw new ApiCustomException(HttpStatus.BAD_REQUEST, "Não foi possível criar a base de dados.");
        }
        
    }

    /**
     * Cria um schema em um banco de dados específico.
     *
     * @param connection Conexão com o banco de dados.
     * @param schemaName Nome do schema a ser criado.
     * @throws SQLException Se ocorrer um erro durante a execução do SQL.
     */
    public static void createSchema(Connection connection, String schemaName) throws SQLException {
        String sql = "CREATE SCHEMA IF NOT EXISTS " + schemaName;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Schema '" + schemaName + "' criado com sucesso!");
        }
    }

}
