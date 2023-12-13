package com.library.tijoLibrary.validators;

import org.jetbrains.annotations.NotNull;

public class PasswordValidator {
    public boolean passwordValidation(@NotNull String password){
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        return hasLetter && hasNumber;

    }
}
