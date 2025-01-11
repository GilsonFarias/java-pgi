package com.company.pgi.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Permissions {
    @Id
    @Column(name="key_code", length = 9)
    private String keyCode;

    @NonNull
    @Column(length = 40)
    private String permission;

    @Column(length = 60)
    private String description;

    public Permissions(){}

    public Permissions( String keyCode, String permission, String description ){
        this.keyCode = keyCode;
        this.permission = permission;
        this.description = description;
    }

    public String getkeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}