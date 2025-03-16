package com.company.pgi.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "permissions_profile", schema = "bd_pgi", uniqueConstraints = @UniqueConstraint(name = "index1", columnNames = {
        "key_code", "profile_id" }))
public class PermissionsProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "key_code", referencedColumnName = "key_code", foreignKey = @ForeignKey(name = "fk_permissions"))
    private Permissions permissions;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_profile"))
    private Profile profile;

    @Column(name = "status", nullable = false)
    private Boolean status;

    // Construtor padrão
    public PermissionsProfile() {
    }

    // Construtor com parâmetros
    public PermissionsProfile(Long id, Permissions permissions, Profile profile, Boolean status) {
        this.id = id;
        this.permissions = permissions;
        this.profile = profile;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PermissionsProfile that = (PermissionsProfile) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(permissions, that.permissions) &&
                Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissions, profile, status);
    }

    @Override
    public String toString() {
        return "PermissionsProfile{" +
                "permissions=" + permissions +
                ", profile=" + profile +
                ", status=" + status +
                '}';
    }
}
