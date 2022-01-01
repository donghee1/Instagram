package h.project.repo;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisClient {

	@Resource
	private RedisTemplate<String, Object> redisMgTemplate;
	
	public Set<String> hkeys(String key) {
        return redisMgTemplate.<String, Object>opsForHash().keys(key);
    }
 
    public Map<String, Object> hgetAll(String key) {
        return redisMgTemplate.<String, Object>opsForHash().entries(key);
    }
    
    public Object hget(String key, String field) {
    	return redisMgTemplate.opsForHash().get(key, field);
    }
 
    public Object get(String key) {
        return redisMgTemplate.opsForValue().get(key);
    }
    
    public void hset(String key, String field, Object value) {
    	redisMgTemplate.opsForHash().put(key, field, value);
    }
    
    public void set(String key, Object value) {
    	redisMgTemplate.opsForValue().set(key, value);
    }

	
}
