package dev.mashei.featuretoggledemo.service.data;

import dev.mashei.featuretoggledemo.model.SessionsContainer;
import reactor.core.publisher.Flux;

public interface SessionsProvider {

    Flux<SessionsContainer> getProjectSessions(Integer projectId);

}
