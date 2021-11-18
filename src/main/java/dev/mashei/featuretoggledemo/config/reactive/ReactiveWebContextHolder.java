package dev.mashei.featuretoggledemo.config.reactive;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class ReactiveWebContextHolder implements WebFilter, RequestContextHolder {

    private static final String USER_ID_HEADER = "USER_ID";

    ContextContainer contextContainer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String userId;
        List<String> headers = exchange.getRequest().getHeaders().get(USER_ID_HEADER);
        if (headers != null && headers.size() == 1) {
            userId = headers.get(0);
        } else {
            userId = "0";
        }
        contextContainer = new ContextContainer(userId);
        return chain.filter(exchange);
    }

    @Override
    public String getRequestContext() {
        return contextContainer.getUserId();
    }

    @Data
    static
    class ContextContainer {
        private final String userId;
    }
}
