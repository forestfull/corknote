package com.forestfull.config;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptConfigTest {

    private StringEncryptor encryptor;

    @Qualifier(value = "jasyptEncryptor")
    public void setEncryptor(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    private void encrypt(String message) {
    }

    private void decrypt(String message) {
    }

    @Test
    void encrypt() {
        encrypt("test");
    }
}