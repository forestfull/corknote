package com.forestfull.tunneling;

import com.forestfull.logger.spring.Observable;
import com.forestfull.logger.util.Log;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final Test test;
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

        int randomPort = session.setPortForwardingL(0, "localhost", database.getPort());



        Log.info("connected to database");

        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url("jdbc:mariadb://localhost:" + randomPort + "/" + database.getSchema())
                .username(database.getUsername())
                .password(database.getPassword())
                .driverClassName(database.getDriverClassName())
                .build();
    }

    @PreDestroy
    public void stopSshTunnel() {
        if (session != null && session.isConnected()){
            session.disconnect();
            Log.info("Closing session");
        }
    }
}
