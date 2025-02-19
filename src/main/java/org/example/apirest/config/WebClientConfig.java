package org.example.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {
    @Bean
    public WebClient.Builder laravelApi() {
        return WebClient.builder()
                .baseUrl("http://app.test/api/roles/notify");
    }
}
