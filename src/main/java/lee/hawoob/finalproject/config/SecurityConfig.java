package lee.hawoob.finalproject.config;

import lee.hawoob.finalproject.oauth.OAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/css/**", "/font/**", "/images/**", "/js/**", "/").permitAll()
                .antMatchers("/create", "/passage","/bookshelf","/review", "/mypage").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/")
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/")
                .defaultSuccessUrl("/signup")
                .userInfoEndpoint()
                .userService(userService);
    }
}
