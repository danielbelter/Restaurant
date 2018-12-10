package com.app.restaurantgit.service;

import com.app.restaurantgit.model.AppUser;
import com.app.restaurantgit.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerNewUser(AppUser appUser, HttpServletRequest request) {
        if (appUser == null) {
            throw new NullPointerException("APP USER IS NULL");
        }

        appUser.setEnabled(true);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);


    }
}
