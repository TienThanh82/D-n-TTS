package vn.titv.spring.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserConfiguration {


    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.authorizeHttpRequests(
                    configurer->configurer
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("THUCTAP", "CHUYENVIEN", "LEAD")
                            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("THUCTAP", "CHUYENVIEN", "LEAD")
                            .requestMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("CHUYENVIEN", "LEAD")
                            .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyRole("CHUYENVIEN", "LEAD")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("LEAD")
            );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf->csrf.disable());
            return http.build();
    }
}
