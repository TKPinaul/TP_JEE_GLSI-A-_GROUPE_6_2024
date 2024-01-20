/*
package glsia6.com.compteManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity httpSecur) throws Exception {
        httpSecur.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/clients/public")
                .permitAll()
                .anyRequest()
                .authenticated()
        ).httpBasic(withDefaults());

        return httpSecur.build();
    }
}
*/
