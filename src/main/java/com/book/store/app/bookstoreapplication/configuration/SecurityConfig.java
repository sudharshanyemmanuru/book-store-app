package com.book.store.app.bookstoreapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/signup").permitAll()
                        .requestMatchers("/create-new-account").permitAll()
                                .requestMatchers("/home/**").authenticated()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/profile").authenticated()
                                .requestMatchers("/upload-profile-pic").authenticated()
                                .requestMatchers("/show-profile-pic/**").permitAll()
                                .requestMatchers("/show-book-cover-img/**").authenticated()
                                .requestMatchers("/view-book-details/**").authenticated()
                                .requestMatchers("/add-to-cart/**").authenticated()
                                .requestMatchers("/cart").authenticated()
                                .requestMatchers("/remove-from-cart/**").authenticated()
                                .requestMatchers("/fetch-books").authenticated()
                                .requestMatchers("/post-review").authenticated()
                                .requestMatchers("/place-order").authenticated()
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/email/verify/**").permitAll()
                                .requestMatchers("/payment/**").authenticated()
                )
                .formLogin(login->login.loginPage("/login")
                        .defaultSuccessUrl("/home/page/1")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
               .logout(logout->logout.logoutSuccessUrl("/login?logout=true").permitAll())
               .build();
    }//
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
