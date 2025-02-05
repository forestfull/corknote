package com.forestfull.tunneling;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

	private Session session;
	private final TunnelingProperties properties;

	@Bean
	DataSource dataSource() throws JSchException {
		TunnelingProperties.Ssh ssh = properties.getSsh();
		TunnelingProperties.Database database = properties.getDatabase();

		final JSch jsch = new JSch();
		session = jsch.getSession(ssh.getUsername(), ssh.getHost(), ssh.getPort());
		session.setPassword(ssh.getPassword());
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();

		int randomPort = session.setPortForwardingL(database.getPort(), ssh.getHost(), database.getPort());

		HikariConfig config = new HikariConfig();
		config.addDataSourceProperty("connectionTestQuery", "SELECT 1");
		config.setValidationTimeout(5000);
		config.setJdbcUrl("jdbc:mariadb://mailcowdockerized-mysql-mailcow-1:" + randomPort + "/" + database.getSchema());
		config.setUsername(database.getUsername());
		config.setPassword(database.getPassword());
		config.setDriverClassName(database.getDriverClassName());

		config.setMaximumPoolSize(10);
		config.setMinimumIdle(2);
		config.setConnectionTimeout(30000);
		config.setIdleTimeout(600000);
		config.setMaxLifetime(1800000);

		HikariDataSource hikariDataSource = new HikariDataSource(config);
		try {
			hikariDataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} return hikariDataSource;
	}

	@PreDestroy
	public void stopSshTunnel() {
		if (session != null && session.isConnected())
			session.disconnect();
	}
}
