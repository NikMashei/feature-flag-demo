package dev.mashei.featuretoggledemo.repository;

import dev.mashei.featuretoggledemo.model.ProjectContainer;
import reactor.core.publisher.Flux;

public interface ProjectRepository {

    Flux<ProjectContainer> getProjects();

}
