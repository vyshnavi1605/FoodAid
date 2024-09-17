package com.FoodAid.Group35.config;

import com.FoodAid.Group35.filters.JwtRequestFilter;
import com.FoodAid.Group35.model.FoodBean;
import com.FoodAid.Group35.services.MyUserDetailsService;
import com.FoodAid.Group35.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService);
    }

    /*
    *I granted authorities for each user
    * we have 3 users, and food bank manager can only access the /foodbank api.
    * and restuarant and supermarket manager can only access the /posting api
    * everyone can access auth endpoint due login and sign up purposes.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/foodbank").hasRole("FOOD BANK MANAGER")
                .antMatchers("/posting").hasAnyRole("RESTAURANT MANAGER", "SUPERMARKET MANAGER")
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/api/**").permitAll().
                anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtUtil jwtTokenUtil(){
        return new JwtUtil();
    }

    @Bean
    public FoodBean getFoodBean() { return new FoodBean(); }
}
