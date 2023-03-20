package com.gourav.foodapp.configuration;

import com.gourav.foodapp.globaldata.GlobalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    //authenticate
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //authorize

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/","/mylogin","/logout","/register","/menu").permitAll()
                .and()
                .formLogin()
                .loginPage("/mylogin")
                .loginProcessingUrl("/dologin").defaultSuccessUrl("/menu",true)
                .and()
                .oauth2Login().defaultSuccessUrl("/menu",true)
                .and()
                .logout()
                .addLogoutHandler(new CustomLogoutHandler())
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/mylogin")
                .and()
                .csrf().disable()
                ;

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    static class CustomLogoutHandler implements LogoutHandler {
        @Override
        public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
            System.out.println("logout done");
            GlobalData.cart.clear();

        }
    }
}
