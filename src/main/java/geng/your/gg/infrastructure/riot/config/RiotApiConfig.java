package geng.your.gg.infrastructure.riot.config;

import geng.your.gg.infrastructure.riot.property.RiotApiProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RiotApiProperty.class)
public class RiotApiConfig {

}
