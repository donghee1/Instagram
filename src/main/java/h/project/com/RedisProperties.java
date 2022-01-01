package h.project.com;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.redis")
@Component
public class RedisProperties {
	private String host; //redis 호스트 정보
	private int port; // redis 포트 정보
}
