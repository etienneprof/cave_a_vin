package fr.eni.caveavin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
            auth
                .requestMatchers("/auth").permitAll()
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

        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
