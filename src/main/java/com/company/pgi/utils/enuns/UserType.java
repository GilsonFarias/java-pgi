package com.company.pgi.utils.enuns;

public enum UserType {
    N1(1, "N1", "Adm do Sistema"),
    N2(2, "N2", "Adm do grupo empresarial"),
    N3(3, "N3", "Adm da empresa"),
    N4(4, "N4", "Usuário");

    // ADM_SYSTEM(1, "N1", "Adm do Sistema"),
    // ADM_GROUP_COMPANY(2, "N2", "Adm do grupo empresarial"),
    // ADM_COMPANY(3, "N3", "Adm da empresa"),
    // USER_COMMON(4, "N4", "Usuário");

    private final int id;
    private final String code;
    private final String description;

    UserType(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Método para buscar um UserType pela descrição
    public static UserType typeDescription(String description) {
        for (UserType type : values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type;
            }
        }
        return null; // ou lançar uma exceção se preferir
    }

    // Método para buscar um UserType pelo código (N1, N2, etc.)
    public static UserType typeCode(String code) {
        for (UserType type : values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
