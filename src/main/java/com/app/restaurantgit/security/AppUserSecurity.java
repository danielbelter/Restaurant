package com.app.restaurantgit.security;

import com.app.restaurantgit.model.AppUser;
import com.app.restaurantgit.model.Role;
import com.app.restaurantgit.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
@Qualifier("appUserSecurity")
public class AppUserSecurity implements UserDetailsService {

    private AppUserRepository appUserRepository;

    public AppUserSecurity(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(s);
        System.out.println(appUser);
        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getEnabled(),
                true,
                true,
                true,
                getAuthorities(appUser.getRole())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return Arrays.asList(new SimpleGrantedAuthority(role.getDescription()));
    }
}
