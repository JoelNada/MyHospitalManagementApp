package com.joel.Practice.model.dto.AuthRegisterDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class AuthRegisterDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
}
