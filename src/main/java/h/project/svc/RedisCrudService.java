package h.project.svc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import h.project.dto.RedisCrudSaveRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisCrudService {
	
	private final StringRedisTemplate redisTemplate;
	@Transactional
	public void RedisUserSave(RedisCrudSaveRequestDto requestDto) {
		final String key = "user";
		HashOperations<String, Object, String> stringObjectObjectHashOperations = redisTemplate.opsForHash();
		List <String> setList = new ArrayList<>(Arrays.asList(requestDto.getUserEmail(), requestDto.getUserName(), requestDto.getUserPhone(),
				requestDto.getUserGender(), requestDto.getUserLogin()));
		System.out.println("Rq:::::"+setList);
		stringObjectObjectHashOperations.put(key, requestDto.getUserEmail(), setList.toString());
		
		String get = stringObjectObjectHashOperations.get(key, requestDto.getUserEmail());
		String[] getList = get.split("\\[|,\\s|]");
		
		for(int i = 1; i<getList.length; i++) {
			System.out.println(getList[i]);
		}

		
	}
}
