package com.eon.demo.contactkeeperapi.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.eon.demo.secret")
public class SecretProperties {

    private String key = "y,jdfhsoidlfjsefyaslidfj39875skjdfnjx,mhrakweb8!";

}
