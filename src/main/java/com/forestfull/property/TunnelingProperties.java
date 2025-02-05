package com.forestfull.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(value = "tunneling")
public class TunnelingProperties {

	private Database database;
	private Ssh ssh;

	@Setter
	@Getter
	public static class Database {
		private String driverClassName;
		private String url;
		private String username;
		private String password;
	}

	@Setter
	@Getter
	public static class Ssh {
		private String host;
		private String user;
		private String password;
	}

}
