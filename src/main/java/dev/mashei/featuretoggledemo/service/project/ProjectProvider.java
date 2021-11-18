package dev.mashei.featuretoggledemo.service.project;

import dev.mashei.featuretoggledemo.model.ProjectItem;
import reactor.core.publisher.Flux;

public interface ProjectProvider {

    Flux<ProjectItem> getProjectItems();

}
