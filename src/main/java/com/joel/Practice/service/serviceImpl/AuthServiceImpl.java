package com.joel.Practice.service.serviceImpl;

import com.joel.Practice.exceptions.customExceptions.BadCredentials;
import com.joel.Practice.exceptions.customExceptions.RoleNotFoundException;
import com.joel.Practice.exceptions.customExceptions.UserAlreadyExists;
import com.joel.Practice.model.dto.AuthLoginRequestDTO;
import com.joel.Practice.model.dto.AuthLoginResponseDTO;
import com.joel.Practice.model.dto.AuthRegisterDTO.AuthRegisterDTO;
import com.joel.Practice.model.entity.Role;
import com.joel.Practice.model.entity.User;
import com.joel.Practice.repo.RoleRepo;
import com.joel.Practice.repo.UserRepo;
import com.joel.Practice.service.AuthService;
import com.joel.Practice.service.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    private User addToEntity(AuthRegisterDTO authRegisterDTO){
        return modelMapper.map(authRegisterDTO,User.class);
    }



    @Override
    public String registerAdmin(AuthRegisterDTO authRegisterDTO) {
        User checkUser = userRepo.findByUserName(authRegisterDTO.getUserName());
        if(checkUser!=null){
            throw new UserAlreadyExists("User already exists !!");
        }
        else{
            User user = addToEntity(authRegisterDTO);
            Role userRole=roleRepo
                    .findByRole("ROLE_ADMIN")
                    .orElseThrow(()-> new RoleNotFoundException("Role Not Found !!"));
            user.getRoles().add(userRole);
            user.setPassword(passwordEncoder.encode(authRegisterDTO.getPassword()));
            userRepo.save(user);
            return "Registered Admin Successfully!!";
        }
    }

    @Override
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @Override
    public AuthLoginResponseDTO login(AuthLoginRequestDTO requestDTO) {
        Authentication authentication = manager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        requestDTO.getUserName(),
                        requestDTO.getPassword())
                );
        if(authentication.isAuthenticated()){
            AuthLoginResponseDTO responseDTO = new AuthLoginResponseDTO();
            UserDetails userDetails = userDetailsService.loadUserByUsername(requestDTO.getUserName());
            String token=jwtService.generateToken(userDetails);
            responseDTO.setToken(token);
            return responseDTO;
        }
            else {
            throw new BadCredentials("Invalid Username or Password!");
        }
    }
}
