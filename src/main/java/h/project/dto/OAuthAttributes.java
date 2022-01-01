package h.project.dto;

import java.util.Map;

import h.project.dao.UserLogin;
import lombok.Data;

@Data
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	public OAuthAttributes(Map<String, Object> attributes,String nameAttributeKey,String name,String email, String picture ) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	
	//로그인 처리하려는 서비스가 kakao인지 google인지 구분하여 매핑
	//registrationId는 로그인하려는 서비스 명
	//userNameAttributeName은 해당 서비스의 map의 키값(kakao = id, google = sub)
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,Map<String, Object> attributes ) {
		if (registrationId.equals("kakao")) {
			return ofKakao(userNameAttributeName, attributes);
		}else if(registrationId.equals("google")){
			return ofGoogle(userNameAttributeName, attributes);
		/*}else if(registrationId.equals("facebook")) {
			System.out.println("aaaaaaa");
			return ofFacebook(userNameAttributeName, attributes);
		}*/
		}
		return null;
	}
	//kakao
	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
		return new OAuthAttributes(attributes, 
				userNameAttributeName,
				(String) profile.get("nickname"),
				(String) kakao_account.get("email"),
				(String) profile.get("profile_image_url"));
	}
	//facebook
	/*private static OAuthAttributes ofFacebook(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
		return new OAuthAttributes(attributes, 
				userNameAttributeName,
				(String) profile.get("name"),
				(String) kakao_account.get("email"),
				(String) profile.get("picture"));
	}*/
	
	//Google
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return new OAuthAttributes(attributes, 
				userNameAttributeName,
				(String) attributes.get("name"),
				(String) attributes.get("email"),
				(String) attributes.get("picture"));
	} 
	
	public UserLogin toEntity() {
		return new UserLogin(name, email, picture);
	}
	
}
