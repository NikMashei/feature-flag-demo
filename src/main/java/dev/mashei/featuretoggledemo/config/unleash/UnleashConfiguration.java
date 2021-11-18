package dev.mashei.featuretoggledemo.config.unleash;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.UnleashContextProvider;
import io.getunleash.util.UnleashConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UnleashConfiguration {

    private final UnleashContextProvider contextProvider;

    @Bean
    public UnleashConfig unleashConfig(
            @Value("${unleash.appName}") String appName,
            @Value("${unleash.instanceId}") String instanceId,
            @Value("${unleash.apiUrl}") String apiUrl,
            @Value("${unleash.clientSecret}") String clientSecret) {
        return UnleashConfig.builder()
                .appName(appName)
                .instanceId(instanceId)
                .unleashAPI(apiUrl)
                .unleashContextProvider(contextProvider)
                .customHttpHeader("Authorization", clientSecret)
                .build();
    }

    @Bean
    public Unleash unleash(UnleashConfig unleashConfig) {
        return new DefaultUnleash(unleashConfig);
    }

}
