package dev.mashei.featuretoggledemo.config.unleash;

import dev.mashei.featuretoggledemo.config.reactive.RequestContextHolder;
import io.getunleash.UnleashContext;
import io.getunleash.UnleashContextProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomContextProvider implements UnleashContextProvider {

    private final RequestContextHolder requestContextHolder;

    @Override
    public UnleashContext getContext() {
        return UnleashContext.builder().userId(requestContextHolder.getRequestContext()).build();
    }

}
