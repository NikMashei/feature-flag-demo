package dev.mashei.featuretoggledemo.unleash;

import io.getunleash.Unleash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToggleComponent {

    private final Unleash unleash;

    public boolean isOrganicFilterEnabled() {
        return unleash.isEnabled("OrganicFilterToggle");
    }

    public boolean isOrganicFilterAvailableForUsers() {
        return unleash.isEnabled("OrganicFilterRollOutToggle");
    }

    public boolean isProjectsListCanaryEnabled() {
        return unleash.isEnabled("ProjectsCanaryToggle");
    }

    public boolean isProjectModelV2Enabled() {
        return unleash.isEnabled("ProjectModelV2Toggle");
    }

}
