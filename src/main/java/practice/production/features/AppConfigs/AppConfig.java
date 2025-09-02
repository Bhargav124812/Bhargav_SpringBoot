package practice.production.features.AppConfigs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import practice.production.features.author.AuditorName;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditor")
public class AppConfig {

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditor(){
        return new AuditorName();
    }
}
