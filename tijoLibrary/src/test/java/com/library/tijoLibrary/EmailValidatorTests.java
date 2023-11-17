package com.library.tijoLibrary;

import com.library.tijoLibrary.validators.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EmailValidatorTests {

    EmailValidator validator = new EmailValidator();
    @Test
    public void testValidEmail() {
        assertTrue(validator.isValid("jan.kowalski@example.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(validator.isValid("niepoprawny_adres_email"));
    }
    @Test
    public void testInvalidEmptyEmail() {
        assertFalse(validator.isValid(""));
    }
}