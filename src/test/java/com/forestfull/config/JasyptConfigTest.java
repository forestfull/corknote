package com.forestfull.config;

import com.forestfull.log.up.util.Log;
import com.forestfull.prop.TunnelingProperties;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

@SpringBootTest
class JasyptConfigTest {

    private StringEncryptor encryptor;
    private TunnelingProperties tunnelingProperties;

    @Autowired
    @Qualifier(value = "jasyptEncryptor")
    public void setEncryptor(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Autowired
    public void setTunnelingProperties(TunnelingProperties tunnelingProperties) {
        this.tunnelingProperties = tunnelingProperties;
    }

    private void encrypt(Object message) {
        Log.info(message, " = ENC(", encryptor.encrypt(String.valueOf(message)), ")");
    }

    private void decrypt(Object message) {
        Log.info(message, " = ", encryptor.decrypt(String.valueOf(message)));
    }

    @Test
    void encrypt() {
        final Supplier<TunnelingProperties> encryptionTunnelingProp = () -> {
            TunnelingProperties.Ssh ssh = tunnelingProperties.getSsh();
            TunnelingProperties.Database database = tunnelingProperties.getDatabase();

            encrypt(database.getDriverClassName());
            encrypt(database.getSchema());
            encrypt(database.getPort());
            encrypt(database.getUsername());
            encrypt(database.getPassword());
            System.out.println();
            encrypt(ssh.getHost());
            encrypt(ssh.getPort());
            encrypt(ssh.getUsername());
            encrypt(ssh.getPassword());
            return tunnelingProperties;
        };
//        encryptionTunnelingProp.get();
    }

    @Test
    void decrypt() {
        Log.info(tunnelingProperties);

    }
}