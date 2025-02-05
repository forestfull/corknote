package com.forestfull;

import com.forestfull.tunneling.TunnelingProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {TunnelingProperties.class})
public class CorknoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorknoteApplication.class, args);
	}

}
