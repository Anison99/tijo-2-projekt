package com.library.tijoLibrary.services;

import com.library.tijoLibrary.models.User;
import com.library.tijoLibrary.repositories.UserRepository;
import com.library.tijoLibrary.validators.EmailValidator;
import com.library.tijoLibrary.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordValidator passwordValidator;

    // Pobieranie użytkownika po ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /*
    // Ładowanie użytkownika po nazwie użytkownika
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

     */

    // Rejestracja nowego użytkownika
    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        if(username != null
                && !username.isEmpty()
                && username.length() > 8
                && password != null
                && !password.isEmpty()
                && password.length() > 8
        )
        {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return userRepository.save(user);
        }
        return null;
    }


    // Pobieranie danych logowania użytkownika
    public Optional<User> getUserByLoginDetails(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // Zmiana adresu email użytkownika
    public void changeUserEmail(Long userId, String newEmail) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newEmail);
        userRepository.save(user);
        // Wysłanie powiadomienia emailowego (wymaga implementacji)
    }

    // Usunięcie użytkownika
    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
    }

    // Zmiana nazwy użytkownika
    public User updateUserName(String oldUsername, String usernamChange) {
        User user = userRepository.findByUsername(oldUsername)
                .orElseThrow(() -> new EntityNotFoundException("User with username: " + oldUsername + " not found"));
        user.setUsername(usernamChange);
        return userRepository.save(user);
    }

    // Zmiana hasła
    public User updateUserPassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + username + " not found"));
        user.setPassword(newPassword); // powinno być zahashowane w praktyce
        return userRepository.save(user);
    }

    // Zmiana adresu e-mail
    public User updateUserEmail(String username, String newEmail) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + username + " not found"));
        user.setEmail(newEmail);
        return userRepository.save(user);
    }
    public User addEmail(String username, String newEmail) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + username + " not found"));
        user.setEmail(newEmail);
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User updateUser(Long userId, User userDetails) {
        User user = getUserById(userId);
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }
    public User loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

}
