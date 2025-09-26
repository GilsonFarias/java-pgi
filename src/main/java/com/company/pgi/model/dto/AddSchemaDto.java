package com.company.pgi.model.dto;

public class AddSchemaDto {
    private String module;
    private String cnpj;
    
    public AddSchemaDto() {}
    
    public AddSchemaDto(String module, String cnpj) {
        this.module = module;
        this.cnpj = cnpj;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
