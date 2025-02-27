package com.forestfull.config;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Bean(name = "jasyptEncryptor")
    StringEncryptor stringEncryptor(@Value("${jasypt.encryptor.password}") String password) {
        final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setPassword(password);
        encryptor.setIvGenerator(new RandomIvGenerator());
        encryptor.setPoolSize(1);

        return encryptor;
    }
}