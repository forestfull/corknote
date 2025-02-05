package com.forestfull.config;

import com.forestfull.logger.util.Log;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

	@Value("${salt}")
	private String salt;

	@Bean(name = "jasyptEncryptor")
	StringEncryptor stringEncryptor() {
		final SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(salt);
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
		config.setStringOutputType("base64");

		final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(config);

		return encryptor;
	}

	private void generateEncrypt(PooledPBEStringEncryptor encryptor, String message) {
		Log.info(message, "=", "ENC(", encryptor.encrypt(message), ")");
	}
}
