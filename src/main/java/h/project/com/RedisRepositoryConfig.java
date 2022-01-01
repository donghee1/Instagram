package h.project.com;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
@ConfigurationProperties(prefix = "spring.redis")
@Data
@Component
//저장 및 조회
public class RedisRepositoryConfig {
	//redis 연결 정보 담긴 클래스
	
		private String host; //redis 호스트 정보
		private int port; // redis 포트 정보

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		
		
		//redis 호스트, 포트 Lettuce로 연결
				return new LettuceConnectionFactory(host, port);
	}
	
	@Bean
	//RedisTemplate bean 등록 --> byte 값 객체직렬화
	public RedisTemplate<?, ?> redisTemplate(){
		RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}
}
