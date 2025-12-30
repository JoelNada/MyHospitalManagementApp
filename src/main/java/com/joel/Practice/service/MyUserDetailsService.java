package com.joel.Practice.service;

import com.joel.Practice.model.UserPrincipal;
import com.joel.Practice.model.entity.User;
import com.joel.Practice.repo.UserRepo;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    
    private UserRepo userRepo;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);

        if(user==null){
            throw new UsernameNotFoundException("User not found!!");
        }

        return new UserPrincipal(user);
    }
}
