//package h.project.dao;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@Table(name = "USER")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class AccountDao {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long sid;
//	
//	@Column(name = "user_email", unique = true)
//	private String email;
//	
//			
//	@Column(name = "user_name" )
//	private String name;
//	@Column(name = "user_password" )
//	private String password;
//	@Column(name = "user_phone" )
//	private String phone;
//	@Column(name = "user_gender" )
//	private String gender;
//	@Column(name = "user_login" )
//	private String login;
//	
//	@Builder
//	public AccountDao(String name, String password, String email, String phone, String gender, String login) {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.phone = phone;
//        this.gender = gender;
//        this.login = login;
//    }
//	
//}
