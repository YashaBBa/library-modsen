package com.modsen.library.config;

import com.modsen.library.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtSecurityConfig jwtSecurityConfig;
    private final UserDao userDao;


    @Bean
    SecurityFilterChain securityFilter(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(t-> t.disable());
        httpSecurity.authorizeRequests(auth-> auth
                .antMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
                .regexMatchers(HttpMethod.POST,"/api/v1/auth").permitAll()
                .anyRequest().authenticated()
        );
        httpSecurity.sessionManagement(
                t->t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        httpSecurity.authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtSecurityConfig,UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder(12);
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

                return userDao.findUserByEmail(email);
            }
        };
    }

}
