package h.project.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter{

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
				.withClient("testClientId")
				.secret("test")
				.redirectUris("http://localhost:8080/main")
				.authorizedGrantTypes("authorization_code")
				.scopes("read", "write")
				.accessTokenValiditySeconds(30000);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		System.out.println("restTemp Start !!!!");
		return new RestTemplate();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("PasswordEncoder Start !!!!");
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
