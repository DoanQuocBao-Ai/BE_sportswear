package com.store.sportswear.config;

import com.store.sportswear.security.JwtAuthenticationEntryPoint;
import com.store.sportswear.security.JwtAuthenticationFilter;
import com.store.sportswear.service.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint handler;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Autowired
    public  void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
    {
        try{
            authenticationManagerBuilder.inMemoryAuthentication()
                    .withUser("doanquocbao").password("{noop}123").roles("USER")
                    .and()
                    .withUser("baris").password("{noop}123").roles("ADMIN");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                        .csrf().disable()
                        .authorizeRequests()
                        .antMatchers("/register").permitAll()
                        .antMatchers("/").permitAll()
                        .antMatchers("/api/*").hasRole("ADMIN")
                        .antMatchers("/user").hasRole("USER")
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(successHandler)
                        .failureUrl("/login?error")
                        .and()
                        .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .and()
                        .rememberMe().key("uniqueAndSecret").rememberMeParameter("remember-me")
                        .and()
                        .exceptionHandling().accessDeniedPage("/login?accessDenied");
     }
}
