package com.joel.Practice.controller;

import com.joel.Practice.model.dto.AuthLoginRequestDTO;
import com.joel.Practice.model.dto.AuthLoginResponseDTO;
import com.joel.Practice.model.dto.AuthRegisterDTO.AuthRegisterDTO;
import com.joel.Practice.model.dto.PatientDTO;
import com.joel.Practice.model.entity.User;
import com.joel.Practice.service.AuthService;
import com.joel.Practice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public ResponseEntity<?> auth(){
       String message="Auth controller API main route.";
        return ResponseEntity.ok().body(message);
    }

//    @PostMapping("/register/user")
//    public ResponseEntity<?> registerUser(@RequestBody AuthRegisterDTO authRegisterDTO){
//       String response = authService.registerUser(authRegisterDTO);
//       return ResponseEntity.ok().body(response);
//    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRegisterDTO authRegisterDTO){
        String response = authService.registerAdmin(authRegisterDTO);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-users")
    public ResponseEntity<?> allUsers(){
      return ResponseEntity.ok().body(authService.allUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequestDTO loginDTO){
        AuthLoginResponseDTO responseDTO = authService.login(loginDTO);
        return ResponseEntity.ok().body(responseDTO);
    }


}
