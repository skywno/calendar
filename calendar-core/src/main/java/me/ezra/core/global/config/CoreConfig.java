package me.ezra.core.global.config;

import me.ezra.core.global.util.BCryptEncryptor;
import me.ezra.core.global.util.Encryptor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("me.ezra")
@Configuration
@EnableJpaRepositories("me.ezra")
@EnableJpaAuditing
public class CoreConfig {

    @Bean
    public Encryptor bcryptEncryptor() {
        return new BCryptEncryptor();
    }
}
