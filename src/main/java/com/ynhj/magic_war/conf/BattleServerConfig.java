package com.ynhj.magic_war.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2020-10-20
 * @author: yangniuhaojiang
 * @title: ServerConfig
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Configuration
@ConfigurationProperties(prefix = "battle")
public class BattleServerConfig {

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getPort
     * @description: update_version: update_date: update_author: update_note:
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setPort
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    private Integer port;

}
