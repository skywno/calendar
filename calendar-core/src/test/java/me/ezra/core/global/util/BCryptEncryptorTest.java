package me.ezra.core.global.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptEncryptorTest {


    @Test
    void bCryptTest() {
        final String origin = "my_password";
        final Encryptor encryptor = new BCryptEncryptor();
        final String hashed = encryptor.encrypt(origin);

        assertTrue(encryptor.isMatch(origin, hashed));

        final String wrong = "my_wrong_password";
        assertFalse(encryptor.isMatch(wrong, hashed));
    }
}