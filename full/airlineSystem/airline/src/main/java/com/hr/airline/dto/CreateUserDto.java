package com.hr.airline.dto;

import com.hr.airline.enums.RoleName;

public record CreateUserDto(String email, String name, String password, RoleName role) {

}
