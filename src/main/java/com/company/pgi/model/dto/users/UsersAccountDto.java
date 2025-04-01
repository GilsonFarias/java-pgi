package com.company.pgi.model.dto.users;

public class UsersAccountDto {
    
    private UsersDto usersDto;
    
    private String modelSystem;
    
    public UsersAccountDto(UsersDto usersDto, String modelSystem) {
        this.usersDto = usersDto;
        this.modelSystem = modelSystem;
    } 

    public UsersDto getUsersDto() {
        return usersDto;
    }

    public void setUsersDto(UsersDto usersDto) {
        this.usersDto = usersDto;
    }

    public String getModelSystem() {
        return modelSystem;
    }

    public void setModelSystem(String modelSystem) {
        this.modelSystem = modelSystem;
    }
}
