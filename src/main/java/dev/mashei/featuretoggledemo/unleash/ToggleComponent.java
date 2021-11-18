package dev.mashei.featuretoggledemo.unleash;

import io.getunleash.Unleash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToggleComponent {

    private final Unleash unleash;

    public boolean isProjectsListEnabled() {
        return unleash.isEnabled("ProjectsToggle");
    }

}
