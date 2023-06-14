package com.sisp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        //此处可添加别的规则,目前只设置 允许双 //
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
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

//                                                       "/**",
                                                       "/utils/**",
                                                       "/admin/**",
                                                       "/pages/login/**",
                                                       "/register/**",
                                                       "/static/**",

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
                        .loginPage("/pages/login/index.html")
                        .defaultSuccessUrl("/pages/seeProject/index.html")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );
    }
}
