package com.company.pgi.utils;

import java.util.regex.Pattern;

public class PasswordValidator {

    // Expressão regular para validar senha
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,20}$";


    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

        /**
     * Valida se a senha atende aos critérios definidos.
     * 
     * @param password A senha a ser validada.
     * @return true se for válida, false caso contrário.
     */
    
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return PATTERN.matcher(password).matches();
    }

}
