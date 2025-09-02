package practice.production.features.author;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorName implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Bhargav");
    }
}
