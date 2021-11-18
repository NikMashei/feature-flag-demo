package dev.mashei.featuretoggledemo.web;

import dev.mashei.featuretoggledemo.model.ProjectItem;
import dev.mashei.featuretoggledemo.service.project.ProjectProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ProjectResource {

    private final ProjectProvider projectProvider;

    @GetMapping("projects")
    Flux<ProjectItem> getProjects() {
        return projectProvider.getProjectItems();
    }

}
