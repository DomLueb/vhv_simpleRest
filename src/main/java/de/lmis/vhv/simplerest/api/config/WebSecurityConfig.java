package de.lmis.vhv.simplerest.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private static final String CLIENT_ID="vertrags-service";

    private String[] allowedOrigins = {"http://localhost:8081","http://localhost:8082"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()

                .authorizeRequests()
                .mvcMatchers("contracts/**").permitAll()
                .anyRequest().authenticated()
                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(this::extractAuthorities);
    }

    @SuppressWarnings("unchecked")
    private AbstractAuthenticationToken extractAuthorities(Jwt jwt) {
        List<String> roles = Collections.emptyList();
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            var assignedRolesInService = (Map<String, List<String>>) resourceAccess.get(CLIENT_ID);
            if (assignedRolesInService != null) {
                roles = assignedRolesInService.get("roles");
            }
        }

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> "ROLE_" + role.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new JwtAuthenticationToken(jwt, authorities);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name())
                .allowedOrigins(this.allowedOrigins)
                .exposedHeaders(HttpHeaders.LOCATION)
        ;
    }



}
