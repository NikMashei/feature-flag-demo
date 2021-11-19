package dev.mashei.featuretoggledemo.service.project.impl;

import dev.mashei.featuretoggledemo.model.ProjectItem;
import dev.mashei.featuretoggledemo.repository.ProjectRepository;
import dev.mashei.featuretoggledemo.service.data.SessionsProvider;
import dev.mashei.featuretoggledemo.service.project.ProjectProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("noOrganic")
@RequiredArgsConstructor
@Slf4j
public class ProjectService implements ProjectProvider {

    private final ProjectRepository projectRepository;
    private final SessionsProvider sessionsProvider;

    @Override
    public Flux<ProjectItem> getProjectItems() {
        return projectRepository.getProjects()
                .flatMap(project -> Flux.zip(Mono.just(project), sessionsProvider.getProjectSessions(project.getId()).collectList()))
                .map(tuple -> ProjectItem.from(tuple.getT1(), tuple.getT2()));
    }

}
