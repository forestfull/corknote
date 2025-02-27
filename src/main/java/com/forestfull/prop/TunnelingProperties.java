package com.forestfull.prop;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "datasource.tunneling")
public class TunnelingProperties {

    private Database database;

    public Ssh getSsh() {
        return ssh;
    }

    public void setSsh(Ssh ssh) {
        this.ssh = ssh;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    private Ssh ssh;

    @Setter
    @Getter
    @ToString(exclude = "password")
    public static class Database {
        private String driverClassName;

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String schema;
        private String port;
        private String username;
        private String password;

        public Integer getPort() {
            return Integer.valueOf(port);
        }
    }

    @Setter
    @Getter
    @ToString(exclude = "password")
    public static class Ssh {
        private String host;
        private String port;
        private String username;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getPort() {
            return Integer.valueOf(port);
        }
    }
}
