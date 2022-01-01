package h.project.svc;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import h.project.dao.UserInfo;
import h.project.dto.UserInfoDto;
import h.project.repo.RedisClient;
import h.project.repo.UserRepository;
import lombok.RequiredArgsConstructor;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  // 원래 문법
  // private final UserService userService;
  // 에러내용 : The blank final field userService may not have been initialized
	
  private final UserRepository userRepository;
  private final RedisClient redisClient;
  
  /**
   * Spring Security 필수 메소드 구현
   *
   * @param email 이메일
   * @return UserDetails
   * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
   */
  @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
  public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
    
	  return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException((email)));
  }
  
  @Transactional
  public String createUser(UserInfoDto user) {
      UserInfo userinfo = user.toEntity();
      
      Optional<UserInfo> validateUserId = userRepository.findByEmail(userinfo.getEmail());
      System.out.println("check if = " + validateUserId.isPresent());
      
      if(!validateUserId.isPresent()) {
    	  userRepository.save(userinfo);
      	 //redis
      	 // 레디스저장할 로직 추가
    	  redisClient.hset("userinfo", userinfo.getEmail(), userinfo);
      	
    	  
      } else {
         System.out.println("check email not insert " );

      	String result = "reject";
      	return result;
      }
     
      return userinfo.getEmail();
      
  }
}