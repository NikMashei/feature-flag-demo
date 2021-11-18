package dev.mashei.featuretoggledemo.model;

import lombok.Value;

import java.util.List;

@Value
public class ProjectItem {

    Integer id;
    String name;
    String sessions;

    public static ProjectItem from(ProjectContainer project, List<SessionsContainer> sessionsContainers) {
        String sessionsCount = String.valueOf(sessionsContainers.stream()
                .mapToInt(container -> Integer.parseInt(container.getValue()))
                .sum());

        return new ProjectItem(project.getId(), project.getName(), sessionsCount);
    }

}
