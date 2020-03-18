package com.jrp.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled "+
                        "from user_accounts where username = ?")
                .authoritiesByUsernameQuery("select username, role "+
                        "from user_accounts where username = ?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptEncoder);//decoding encrypted passwords
//                .withDefaultSchema()
//                .withUser("myuser")
//                .password("pass")
//                .roles("USER")
//        .and()
//                .withUser("taz")
//                .password("pass2")
//                .roles("USER")
//        .and()
//                .withUser("managerUser")
//                .password("pass3")
//                .roles("ADMIN");
    }
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/employees/save").hasRole("ADMIN")
//                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/").authenticated().and().formLogin();
                .antMatchers("/","/**").permitAll()
                .and()
                .formLogin()/*.loginPage("/login-page")*/;

        //Make the /h2-console to work, NOT USE IN PRODUCTION and Also allow POST, PUT, PATCH, DELETE (not safe) requests
        http.csrf().disable();
//        http.headers().frameOptions().disable();
    }
}