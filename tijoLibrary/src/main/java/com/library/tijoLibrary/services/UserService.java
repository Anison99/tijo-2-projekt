package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public void registerUser(String username, String password) {
    }

    public boolean isUserRegistered(String username) {
        return false;
    }

    public User login(String username, String password) {
        return null;
    }

    public void addUser(String username, String password) {
    }
}
