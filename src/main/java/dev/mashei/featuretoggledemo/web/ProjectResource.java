package dev.mashei.featuretoggledemo.web;

import dev.mashei.featuretoggledemo.model.ProjectItem;
import dev.mashei.featuretoggledemo.service.project.ProjectProvider;
import dev.mashei.featuretoggledemo.unleash.ToggleComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("projects/sessions")
@Slf4j
public class ProjectResource {

    private final ProjectProvider organicProvider;
    private final ProjectProvider noOrganicProvider;
    private final ToggleComponent projectToggle;

    public ProjectResource(@Qualifier("organic") ProjectProvider organicProvider,
                           @Qualifier("noOrganic") ProjectProvider noOrganicProvider,
                           ToggleComponent projectToggle) {
        this.organicProvider = organicProvider;
        this.noOrganicProvider = noOrganicProvider;
        this.projectToggle = projectToggle;
    }

    @GetMapping
    Flux<ProjectItem> getProjectsWithAllData() {
        return noOrganicProvider.getProjectItems();
    }

    @GetMapping("organic")
    Flux<ProjectItem> getProjectsWithOrganicData() {
        return organicProvider.getProjectItems();
    }

    @GetMapping("organic/feature")
    Flux<ProjectItem> getProjects() {
        if (projectToggle.isOrganicFilterEnabled()) {
            return organicProvider.getProjectItems();
        }
        return noOrganicProvider.getProjectItems();
    }

    @GetMapping("organic/feature/preview")
    ResponseEntity<Flux<ProjectItem>> getProjectsFeature() {
        if (projectToggle.isOrganicFilterAvailableForUsers()) {
            return ResponseEntity.ok(organicProvider.getProjectItems());
        }
        return ResponseEntity.status(501).body(Flux.empty());
    }

    @GetMapping("canary")
    ResponseEntity<Flux<ProjectItem>> getProjectsCanary() {
        log.info("request");
        if (projectToggle.isProjectsListCanaryEnabled()) {
            return ResponseEntity.status(200).body(organicProvider.getProjectItems());
        }
        return ResponseEntity.status(501).body(Flux.empty());
    }
}
