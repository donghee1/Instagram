package h.project.dto;

import java.io.Serializable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import h.project.dao.UserLogin;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//인증된 사용자 정보를 저장하는 클래스 -> HttpSession에 넣을 것임.
@Data
@NoArgsConstructor
public class UserDto{
	private Long userNo;
	private String userEmail;
	private String userPassword;
	private String userName;
	private String userPhone;
	private String userGender;
	private String userState;
	private String userLogin;
	private String userNickname;
	private String userInfo;
	
	public UserLogin toEntity() {
		UserLogin build = UserLogin.builder()
				.userNo(userNo)
				.userEmail(userEmail)
				.userPassword(new BCryptPasswordEncoder().encode(userPassword))
				.userName(userName)
				.userPhone(userPhone)
				.userGender(userGender)
				.userState(userState)
				.userLogin(userLogin)
				.userNickname(userNickname)
				.userInfo(userInfo)
				.build();
		return build;
	}
	
	@Builder
	public UserDto(Long userNo,String userEmail,String userPassword,String userName, String userPhone, 
			String userGender, String userState, String userLogin, String userNickname, String userInfo) {
		System.out.println("userGender:"+userGender);
		this.userNo = userNo;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userGender = userGender;
		this.userState = userState;
		this.userLogin = userLogin;
		this.userNickname = userNickname;
		this.userInfo = userInfo;
	}
}
