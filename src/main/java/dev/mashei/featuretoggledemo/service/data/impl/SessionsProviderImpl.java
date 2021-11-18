package dev.mashei.featuretoggledemo.service.data.impl;

import dev.mashei.featuretoggledemo.model.SessionsContainer;
import dev.mashei.featuretoggledemo.service.data.SessionsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SessionsProviderImpl implements SessionsProvider {

    private final Map<Integer, List<SessionsContainer>> db = Map.of(
            1, List.of(new SessionsContainer("1000", true), new SessionsContainer("2000", false)),
            2, List.of(new SessionsContainer("12000", true), new SessionsContainer("43000", false)),
            3, List.of(new SessionsContainer("24000", true), new SessionsContainer("50000", false))
    );

    @Override
    public Flux<SessionsContainer> getProjectSessions(Integer projectId) {
        if (db.containsKey(projectId)) {
            return Flux.fromIterable(db.get(projectId));
        }
        log.error("No sessions found for project {}", projectId);
        return Flux.empty();
    }

}
