package dev.mashei.featuretoggledemo.service.project.impl;

import dev.mashei.featuretoggledemo.model.ProjectContainer;
import dev.mashei.featuretoggledemo.model.ProjectItem;
import dev.mashei.featuretoggledemo.model.SessionsContainer;
import dev.mashei.featuretoggledemo.repository.ProjectRepository;
import dev.mashei.featuretoggledemo.service.data.SessionsProvider;
import dev.mashei.featuretoggledemo.service.project.ProjectProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service("organic")
@RequiredArgsConstructor
public class ProjectOrganicService implements ProjectProvider {

    private final ProjectRepository repository;
    private final SessionsProvider sessionsProvider;

    @Override
    public Flux<ProjectItem> getProjectItems() {
        return repository.getProjects()
                .flatMap(projectContainer -> {
                    Mono<List<SessionsContainer>> sessions = sessionsProvider.getProjectSessions(projectContainer.getId())
                            .filter(SessionsContainer::isOrganic)
                            .collectList();

                    return Flux.zip(Mono.just(projectContainer), sessions);
                })
                .map(tuple -> {
                    ProjectContainer project = tuple.getT1();
                    List<SessionsContainer> sessions = tuple.getT2();

                    return ProjectItem.from(project, sessions);
                });
    }

}
