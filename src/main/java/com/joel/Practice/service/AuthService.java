package com.joel.Practice.service;

import com.joel.Practice.model.dto.AuthLoginRequestDTO;
import com.joel.Practice.model.dto.AuthLoginResponseDTO;
import com.joel.Practice.model.dto.AuthRegisterDTO.AuthRegisterDTO;
import com.joel.Practice.model.entity.User;


import java.util.List;

public interface AuthService {

    String registerAdmin(AuthRegisterDTO authRegisterDTO);
    public List<User> allUsers();
    public AuthLoginResponseDTO login(AuthLoginRequestDTO requestDTO);
}
