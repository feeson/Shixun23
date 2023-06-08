package cloud.dicsfeesono.shixun.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JsonToUrlEncodedAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //关闭csrf同源策略
                .csrf().disable()
                .authorizeRequests(authorize -> {
                                       authorize
                                               .antMatchers(HttpMethod.POST, "/**").permitAll()
                                               .antMatchers(
                                                       "/swagger-ui/**","/v2/**","/v3/**",
                                                       "/swagger-ui.html", "/swagger-resources/**",
                                                       "/webjars/springfox-swagger-ui/**",

                                                       "/api/login/**",
                                                       "/register/**",

                                                       "/js/**","/font/**","/img/**","/css/**",
                                                       "/*.js",
                                                       "/*.css",
                                                       "/*.jpg", "/*.png",
                                                       "/*.eot", "/*.svg",
                                                       "/*.ttf", "/*.woff", "/*.woff2").permitAll()
                                               .anyRequest().authenticated();
                                   }
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login/")
                        .defaultSuccessUrl("/shop/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );
    }
}
