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
    private Ssh ssh;

    @Getter
    @Setter
    @ToString(exclude = "password")
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
    @ToString(exclude = "password")
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
