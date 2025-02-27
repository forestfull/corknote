package com.forestfull.config;

import com.forestfull.log.up.util.Log;
import com.forestfull.prop.TunnelingProperties;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private Session session;
    private final TunnelingProperties properties;

    @Bean(name = "webDataSource")
    DataSource webDataSource(@Value("${datasource.web.driver-class-name}") String driverClassName
            , @Value("${datasource.web.url}") String url
            , @Value("${datasource.web.username}") String username
            , @Value("${datasource.web.password}") String password) {

        DataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();

        Log.info("connected to webDataSource");

        return dataSource;
    }

    @Bean(name = "mailcowDataSource")
    DataSource mailcowDataSource() throws JSchException {
        TunnelingProperties.Ssh ssh = properties.getSsh();
        TunnelingProperties.Database database = properties.getDatabase();

        final JSch jsch = new JSch();
        session = jsch.getSession(ssh.getUsername(), ssh.getHost(), ssh.getPort());
        session.setPassword(ssh.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        int randomPort = session.setPortForwardingL(0, "localhost", database.getPort());

        DataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url("jdbc:mariadb://localhost:" + randomPort + "/" + database.getSchema())
                .username(database.getUsername())
                .password(database.getPassword())
                .driverClassName(database.getDriverClassName())
                .build();

        Log.info("connected to mailcowDataSource");

        return dataSource;
    }

    @PreDestroy
    void stopSshTunnel() {
        if (session != null && session.isConnected()){
            session.disconnect();
            Log.info("Closing session");
        }
    }
}