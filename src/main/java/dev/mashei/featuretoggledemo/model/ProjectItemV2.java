package dev.mashei.featuretoggledemo.model;

import lombok.Value;

import java.util.UUID;

@Value
public class ProjectItemV2 {

    Integer id;
    String uid;
    String name;
    String sessions;

    public static ProjectItemV2 from(ProjectItem projectItem) {
        return new ProjectItemV2(projectItem.getId(), UUID.randomUUID().toString(), projectItem.getName(), projectItem.getSessions());
    }

}
