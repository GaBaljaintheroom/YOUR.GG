package geng.your.gg.infrastructure.riot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "riot")
public record RiotApiProperty(
    String baseURL,
    String apiKey
) {

}