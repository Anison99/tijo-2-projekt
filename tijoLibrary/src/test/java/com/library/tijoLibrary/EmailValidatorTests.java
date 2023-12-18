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

    @Test
    public void testEmailWithoutAtSymbol() {
        assertFalse(validator.isValid("emailWithoutAtSymbol.com"));
    }

    @Test
    public void testEmailWithDifferentDomains() {
        assertTrue(validator.isValid("test@example.com"));
        assertTrue(validator.isValid("test@example.org"));
        assertTrue(validator.isValid("test@example.net"));
    }

    @Test
    public void testEmailWithSpecialCharacters() {
        assertTrue(validator.isValid("test.email+spam-filter@example.com"));
    }
}