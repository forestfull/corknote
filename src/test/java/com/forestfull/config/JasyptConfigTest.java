package com.forestfull.config;

import com.forestfull.log.up.util.Log;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
class JasyptConfigTest {

    private StringEncryptor encryptor;

    @Autowired
    @Qualifier(value = "jasyptEncryptor")
    public void setEncryptor(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    private void encrypt(String message) {
        Log.info(message, " = ", encryptor.encrypt(message));
    }

    private void decrypt(String message) {
        Log.info(message, " = ", encryptor.decrypt(message));
    }

    @Test
    void encrypt() {
        encrypt("test");
    }
}