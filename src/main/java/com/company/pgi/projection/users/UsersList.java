package com.company.pgi.projection.users;

import com.company.pgi.model.Profile;

public interface UsersList {
    String getLogin();

    String getEmail();

    Profile getProfile();
}
