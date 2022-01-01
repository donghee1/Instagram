package h.project.com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	// password 단방향 암호화 (해시함수 이용)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	
@Override
protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests() //경로, 권한 설정
		.antMatchers("/**").permitAll() // 누구나 접근 허용
		.antMatchers("/").hasRole("USER") //USER, ADMIN만 접근 가능
		.antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
		.anyRequest().authenticated()// 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 함
	.and()
		.formLogin() //로그인 관련 설정
		/*.loginPage("/login") //login 페이지 링크*/
		.defaultSuccessUrl("/user/success") //로그인 성공시 이동될 리다이렉트 주소
		.permitAll()
	.and()
		.logout() //로그아웃 설정
		.logoutSuccessUrl("/login") //로그아웃 성공시 이동될 리다이렉트 주소
		.invalidateHttpSession(true) //로그아웃 성공 후 세션 전체 삭제 여부
		.clearAuthentication(true)
		.deleteCookies("JSESSIONID")
	.and()
		.oauth2Login() //ouath2 로그인을 위해
		.defaultSuccessUrl("/user/success") //oauth2 인증 성공시 이동될 url
		.userInfoEndpoint() //OAuth 2 로그인 성공 후 사용자 정보를 가져올때 설정 담당
		.userService(userService); //소셜 로그인 성공시 다음조치를 진행할 UserService 인터페이스의 구현체를 등록
}

	public void configure(AuthenticationManagerBuilder auth,HttpSecurity http) throws Exception{
		System.out.println("WebSecurityConfig.configure2 = ????" );
		auth.userDetailsService(userService);
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		http.oauth2Login().userInfoEndpoint().userService(userService);
//		auth.inMemoryAuthentication()
//		.withUser("user")
//		.password("password")
//		.roles("USER");
		
	}
	
}
