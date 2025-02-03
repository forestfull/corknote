package com.forestfull;

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

	@Value("${spring.test.username}")
	private String username;

	@Autowired
	public Test(@Qualifier("jasyptEncryptor") StringEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	@Lazy
	@PostConstruct
	public void init() {
		System.out.println(username);

	}

}
