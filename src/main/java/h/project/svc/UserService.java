package h.project.svc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import h.project.dao.Role;
import h.project.dao.UserInfo;
import h.project.dao.UserLogin;
import h.project.dto.OAuthAttributes;
import h.project.dto.UserDto;
import h.project.dto.UserInfoDto;
import h.project.repo.RedisClient;
import h.project.repo.UserLoginRepository;
import h.project.repo.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	// 원래 문법
	// private final UserService userService;
	// 에러내용 : The blank final field userService may not have been initialized

	private final UserRepository userRepository;
	private final UserLoginRepository userLoginRepository;
	private final RedisClient redisClient;
	private final HttpSession httpSession;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * Spring Security 필수 메소드 구현
	 *
	 * @param email 이메일
	 * @return UserDetails
	 * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
	 */
	@Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
	public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException((email)));
	}
	

	@Transactional
	public String createUser(UserInfoDto user) {
		UserInfo userinfo = user.toEntity();

		Optional<UserInfo> validateUserId = userRepository.findByEmail(userinfo.getEmail());
		System.out.println("check if = " + validateUserId.isPresent());

		if (!validateUserId.isPresent()) {
			userRepository.save(userinfo);
			// redis
			// 레디스저장할 로직 추가
			//redisClient.hset("userinfo", userinfo.getEmail(), userinfo);

		} else {
			System.out.println("check email not insert ");

			String result = "reject";
			return result;
		}

		return userinfo.getEmail();

	}
	
	public UserDetails loadUserByUsername1(String userEmail) throws UsernameNotFoundException {
		Optional<UserLogin> userWrapper = userLoginRepository.findByuserEmail(userEmail);
		UserLogin userLogin = userWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if("admin@test.com".equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		}else{
			authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
		}
		
		boolean email = userWrapper.isPresent();
		httpSession.setAttribute("emailYn", email);
		return new User(userLogin.getUserEmail(), userLogin.getUserPassword(), authorities);
	}
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2UserService oauth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oauth2UserService.loadUser(oAuth2UserRequest);
		
		//현재 로그인 진행중인 서비스를 구분하기 위해 문자열로 받는 로직
		String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

		//OAuth2 로그인시 키 값 (구글 : sub, 카카오 : id)이 각각 다르므로 변수로 받아 넣어주는 로직
		String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		//OAuth2 로그인을 통해 가져온 정보를 attribute를 담아주는 of 메소드 로직
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		boolean userInfo = saveOrUpdate(attributes);
		httpSession.setAttribute("emailYn", userInfo);
		//boolean test = (boolean) httpSession.getAttribute("user");
		//System.out.println("test::::::"+test);
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
				, attributes.getAttributes()
				, attributes.getNameAttributeKey());
	}
	
	//DB에서 userEmail 있는지 확인
	private boolean saveOrUpdate(OAuthAttributes attributes) {
		Optional<UserLogin> emailcheck = userLoginRepository.findByuserEmail(attributes.getEmail());
		System.out.println("emailcheck : " + emailcheck);
		boolean email = emailcheck.isPresent();
		//DB에 email이없을경우 fasle, 있을경우 true
		return email;
	}
	
	//KAKAO 계정 없을 시 추가 정보 입력후 Insert
	@Transactional
	public String createKakao(UserDto userDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		return userLoginRepository.save(userDto.toEntity()).getUserEmail();
				//userRepository.save(userInfo);
	}

}
