package com.example.footballshopwebapp.config;

import com.example.footballshopwebapp.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1/auth/**")
                .permitAll()
                .antMatchers("/v1/sms/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/v1/declare/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/v1/declare/findAccountByPhone")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/v1/declare/sendSmsListAccount")
                .permitAll()
                .antMatchers("/v1/address/**")
                .permitAll()
                .antMatchers("/v1/statistical/**")
                .permitAll()
                .antMatchers("/v1/situation/**")
                .permitAll()
//                .antMatchers(HttpMethod.POST, "/api/posts/").hasRole("ADMIN")
                .anyRequest()  // T???t c??? c??c request kh??c ?????u c???n ph???i x??c th???c m???i ???????c truy c???p
                .authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService) // Cung c??p userDetailsService cho spring security
                .passwordEncoder(passwordEncoder());  // cung c???p password encoder
//        authenticationManagerBuilder.inMemoryAuthentication().withUser(userDetailsService.)
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // Password encoder, ????? Spring Security s??? d???ng m?? h??a m???t kh???u ng?????i d??ng
        return new BCryptPasswordEncoder();
    }
}
