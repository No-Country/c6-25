package com.nocountry.ncc625m.auth.service;

import com.nocountry.ncc625m.model.UserEntity;
import com.nocountry.ncc625m.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("Username not found");

        Collection<GrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority(user.getRole().getName()));

        return new User(user.getEmail(), user.getPassword(), authorities);

    }

}
