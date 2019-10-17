package com.richard.studies.security.config.database;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Profile("H2")
public class H2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InitializingBean initializingBean(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        return () -> {
            UserDetails admin = User.withUsername("admin")
                .passwordEncoder(passwordEncoder::encode)
                .password("secret")
                .roles("ADMIN")
                .build();
            userDetailsManager.createUser(admin);

            UserDetails user = User.withUsername("user")
                .passwordEncoder(passwordEncoder::encode)
                .password("secret")
                .roles("USER")
                .build();
            userDetailsManager.createUser(user);
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .httpBasic();
    }
}
