package com.forestfull.config;

import com.forestfull.property.TunnelingProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor
public class tunnelingDatabaseConfig {

	private final TunnelingProperties properties;

	@Bean
	String test(){
		TunnelingProperties.Database database = properties.getDatabase();
		TunnelingProperties.Ssh ssh = properties.getSsh();
		return null;
	}
}
