package h.project.dao;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/* 로그인 사용자 정보를 담을 클래스 */
//객체와 테이블 매핑 (JPA가 관리)
@Entity
//Entity와 매핑할 테이블 지정 (name : 테이블명)
@Table(name = "USER")
@Data
//기본 생성자 자동 추가
//기본생성자의 접근 권한을 protected로 제한
//Entity 클래스를 project 코드에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity클래스를 생성하는 것은 허용
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//implements Serializable
public class UserLogin {
	
	@Id
	// 키본 키 생성을 DB에 위임(AUTO_INCREMENT 와 같음)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long userNo;

	//객체 필드를 테이블 컬럼에 매핑 (name : 컬럼명)
	@Column(name = "user_email", unique=true)
	private String userEmail;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_phone")
	private String userPhone;
	
	@Column(name = "user_gender")
	private String userGender;
	
	@Column(name = "user_state")
	private String userState;
	
	@Column(name = "user_login")
	private String userLogin;
	
	@Column(name = "user_nickname")
	private String userNickname;
	
	@Column(name = "user_info")
	private String userInfo;
	
	//Model 객체 생성시 Builder을 자동으로 추가 
	public UserLogin(Long userNo, String userEmail, String userPassword) {
		this.userNo = userNo;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	
	public UserLogin(String userName, String userEmail, String userLogin) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userLogin = userLogin;
	}
	
	//소셜로그인 후 user_email 없을시 insert
	@Builder
	public UserLogin (Long userNo, String userEmail,String userPassword,String userName, String userPhone, 
			String userGender, String userState, String userLogin, String userNickname, String userInfo) {
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
