package h.project.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.istack.NotNull;

import h.project.dao.UserInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDto {
	
	@NotNull
	private String email;
	private String name;
	private String password;
	private String phone;
	private String gender;
	private String login;
	
	
	@Builder
	public UserInfoDto(String email, String name, String password, String phone, String gender, String login) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.login = login;
    }
	
	 public UserInfo toEntity(){
	        return UserInfo.builder()
	        		.email(email)
	        		.name(name)
	        		//스프링 시큐리티 패스워드 암호화 처리
	        		.password(new BCryptPasswordEncoder().encode(password))
	        		.phone(phone)
	        		.gender(gender)
	        		.login(login)
	        		.build();
	 }
	
}
