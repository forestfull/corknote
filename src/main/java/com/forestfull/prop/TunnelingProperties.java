package com.forestfull.prop;

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
    public static class Ssh {
        private String host;
        private String port;
        private String username;
        private String password;

        public Integer getPort() {
            return Integer.valueOf(port);
        }
    }
}
