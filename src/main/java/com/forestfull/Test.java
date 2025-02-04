package com.forestfull;

import com.forestfull.logger.util.Log;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Test {

	private final StringEncryptor encryptor;

	@Value("${tunneling.database.driver-class-name}")
	private String driverName;

	@Value("${tunneling.database.username}")
	private String username;

	@Value("${tunneling.database.url}")
	private String url;

	@Value("${tunneling.database.password}")
	private String password;

	@Autowired
	public Test(@Qualifier("jasyptEncryptor") StringEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	@Lazy
	@PostConstruct
	public void init() {
		Log.info("ENC(", driverName, ")");
		Log.info("ENC(", url, ")");
		Log.info("ENC(", username, ")");
		Log.info("ENC(", password, ")");

	}

}
