package h.project.com;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import h.project.svc.UserService;
import lombok.RequiredArgsConstructor;

//Lombok 라이브러리에서 제공하는 어노테이션
@RequiredArgsConstructor
/* 1. 추가 작업을 필요로 하는 필드에 대한 생성자 생성
* 2. 초기화 되지 않은 모든 final 필드, @NonNull로 마크돼있는 모든 필드들에 대한 생성자를 자동으로 생성*/

//스프링시큐리티 사용을 위한 어노테이션
@EnableWebSecurity
/* 1. 웹 보안을 활성화 
* 2. 스프링 시큐리티가 WebSecurityConfigurer 구현하거나 
*    컨텍스트의 WebSebSecurityConfigurerAdapter를 확장한 빈으로 설정되어 있어야 함.
* tip : WebSebSecurityConfigurerAdapter를 확장하여 클래스를 설정하는 것이 가장 편하고 자주 쓰임. */

//Spring에서 Bean 등록하기 위한 어노테이션
@Configuration
/* 1. 1개 이상 Bean 등록하고 있음을 명시함.
* 2. @Bean을 사용하는 클래스에는 반드시 해당 어노테이션을 활용하여 해당 클래스에 Bean을
*    등록하고자 함을 명시해주어야 함.*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/**").permitAll() // 누구나 접근 허용
		.antMatchers("/login").hasRole("USER") //USER, ADMIN만 접근 가능
		.antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
		.anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 함
		.and()
		.formLogin()
		//.loginPage("/login")
		.defaultSuccessUrl("/")
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.invalidateHttpSession(true);
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		System.out.println("WebSecurityConfig.configure2 = ????" );
		auth.userDetailsService(userService);
//		auth.inMemoryAuthentication()
//		.withUser("user")
//		.password("password")
//		.roles("USER");
		
	}
	
	
}
