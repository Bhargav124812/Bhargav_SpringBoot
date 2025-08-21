package production_ready_features_spring.production_features.appConfig;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import production_ready_features_spring.production_features.auth.AuditorAwareImple;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditor")
public class AppConfig {

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditor(){
        return new AuditorAwareImple();
    }
}
