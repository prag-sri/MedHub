package com.medhub.medhub.service;

import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private AppUserRepository userRepository;

    public CustomUserDetailsService(AppUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()))
        );
    }
}
