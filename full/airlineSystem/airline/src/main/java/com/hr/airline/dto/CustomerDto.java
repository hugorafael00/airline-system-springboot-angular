package com.hr.airline.dto;

import java.io.Serializable;

import com.hr.airline.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String name;
    private String passportNumber;


    public CustomerDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.passportNumber = user.getPassportNumber();
    }
}
