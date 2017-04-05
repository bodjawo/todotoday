package com.teamtreehouse.todotoday.config;

import com.teamtreehouse.todotoday.service.UserService;
import com.teamtreehouse.todotoday.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserService userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/assets/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .anyRequest().hasRole("USER")
          .and()              // Login form configuration
        .formLogin()
          .loginPage("/login")
          .permitAll()
          .successHandler(loginSuccessHandler())   // Configure success and failure handler methods
          .failureHandler(loginFailureHandler())
          .and()
        .logout()
          .permitAll()
          .logoutSuccessUrl("/login");
  }

  // AuthenticationSuccessHandler ist ein Interface und hat nur eine Methode
  // dehalb kann lambda verwendet werden
  public AuthenticationSuccessHandler loginSuccessHandler() {
    return ((request, response, authentication) -> response.sendRedirect("/"));
  }

  public AuthenticationFailureHandler loginFailureHandler() {
    return ((request, response, exception) -> {
      request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and / or password. Try again.", FlashMessage.Status.FAILURE));
      response.sendRedirect("/login");
    });
  }
}
