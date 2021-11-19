package dev.mashei.featuretoggledemo.web;

import dev.mashei.featuretoggledemo.model.ProjectItem;
import dev.mashei.featuretoggledemo.model.ProjectItemV2;
import dev.mashei.featuretoggledemo.service.project.ProjectProvider;
import dev.mashei.featuretoggledemo.unleash.ToggleComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProjectResourceV2 {

    private final ProjectProvider projectProvider;
    private final ToggleComponent toggleComponent;


    public ProjectResourceV2(@Qualifier("noOrganic") ProjectProvider projectProvider, ToggleComponent toggleComponent) {
        this.projectProvider = projectProvider;
        this.toggleComponent = toggleComponent;
    }

    @GetMapping("v2/projects/sessions")
    ResponseEntity getProject() {
        Flux<ProjectItem> projectItems = projectProvider.getProjectItems();
        if (toggleComponent.isProjectModelV2Enabled()) {
            return ResponseEntity.ok().body(projectItems.map(ProjectItemV2::from));
        }
        return ResponseEntity.ok().body(projectItems);
    }

}
