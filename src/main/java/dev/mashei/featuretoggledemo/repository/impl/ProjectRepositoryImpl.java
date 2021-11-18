package dev.mashei.featuretoggledemo.repository.impl;

import dev.mashei.featuretoggledemo.model.ProjectContainer;
import dev.mashei.featuretoggledemo.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ProjectRepositoryImpl implements ProjectRepository {

    private final List<ProjectContainer> projects = List.of(
            new ProjectContainer(1, "seoquake", "seoquake-owner@seo.com"),
            new ProjectContainer(2, "adidas", "adidas-owner@adidas.com"),
            new ProjectContainer(3, "nike", "nike-owner@nike.com")
    );

    @Override
    public Flux<ProjectContainer> getProjects() {
        return Flux.fromIterable(projects);
    }

}
