package h.project.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RedisCrudSaveRequestDto {

	private Long userNo;
	private String userEmail;
	private String userName;
	private String userPhone;
	private String userGender;
	private String userLogin;
	
	
	@Builder
	public RedisCrudSaveRequestDto(String userEmail, String userName, 
			String userPhone,String userGender, String userLogin){
		//this.userNo = userNo;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userGender = userGender;
		this.userLogin = userLogin;
	}
	
	/*public RedisCrud toRedisHash() {
		return RedisCrud.builder()
				//.userNo(userNo)
				.userEmail(userEmail)
				.userName(userName)
				.userPhone(userPhone)
				.userGender(userGender)
				.userLogin(userLogin)
				.build();
	}*/
}
