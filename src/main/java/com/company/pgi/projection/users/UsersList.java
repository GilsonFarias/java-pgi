package com.company.pgi.projection.users;

import com.company.pgi.model.UserProfile;

public interface UsersList {
    String getLogin();
    String getEmail();
    UserProfile getUserProfile();
}
