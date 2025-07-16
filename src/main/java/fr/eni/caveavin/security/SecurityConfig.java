package fr.eni.caveavin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select login, password, 1 from cav_user where login = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select login, authority from cav_user where login = ?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
            auth
                // Autorisations des clients et administrateurs

                .requestMatchers( HttpMethod.GET,"/caveavin/paniers/**").hasAnyRole("CLIENT", "OWNER")
                .requestMatchers( HttpMethod.GET,"/caveavin/paniers/client/commandes/**").hasAnyRole("CLIENT", "OWNER")
                .requestMatchers( HttpMethod.GET,"/caveavin/paniers/client/actifs/**").hasAnyRole("CLIENT", "OWNER")

                // Autorisations des clients
                .requestMatchers( HttpMethod.POST,"/caveavin/paniers").hasRole("CLIENT")
                .requestMatchers( HttpMethod.PUT,"/caveavin/paniers").hasRole("CLIENT")

                // Autorisations des propriétaires
                .requestMatchers( HttpMethod.POST,"/caveavin/bouteilles").hasRole("OWNER")
                .requestMatchers( HttpMethod.PUT,"/caveavin/bouteilles").hasRole("OWNER")
                .requestMatchers( HttpMethod.DELETE,"/caveavin/bouteilles").hasRole("OWNER")

                .requestMatchers( HttpMethod.GET,"/caveavin/bouteilles").permitAll()
                .anyRequest().denyAll()
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
