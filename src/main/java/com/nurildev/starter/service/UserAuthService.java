package com.nurildev.starter.service;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.entity.User;
import com.nurildev.starter.repository.UserAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserAuthRepo userAuthRepo;

    private List<Role> getRolesById(Long id){
        List<Object[]> objects = userAuthRepo.findRolesByUserId(id);
        List<Role> roles = new ArrayList<>();
        for (int i = 0; i<objects.size(); i++) {
            Object[] obj = objects.get(i);
            Role role = Role.builder()
                    .name((String)obj[2])
                    .build();
            roles.add(role);
        }
        return roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAuthRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s Not Found!", username)));
        List<Role> roles = getRolesById(user.getId());
        Set<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }
        };

    }
}
