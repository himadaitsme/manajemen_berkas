package kpp.payakumbuh.manajemenberkas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private CustomUserDetailsService customUserDetailsService;
//	
//	@Bean
//	public BCryptPasswordEncoder encoder() {
//		
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
//	}
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(customAuthenticationProvider);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/assets/**", "/css/**", "/js/**", "/plugins/**", "djp.ico").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
            	.logoutUrl("/logout")
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")
            	.logoutSuccessUrl("/login")
            	.and()
//            .exceptionHandling()
//            	.accessDeniedPage("/403")
//            	.and()
            .csrf().disable();
    }
    
}