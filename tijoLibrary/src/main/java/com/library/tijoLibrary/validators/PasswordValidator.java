package com.library.tijoLibrary.validators;

import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public boolean passwordValidation(@NotNull String password){
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        return hasLetter && hasNumber;
    }
    public boolean passwordMatches(@NotNull String rawPassword, @NotNull String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }

}
