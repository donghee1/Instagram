//package h.project.dto;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import h.project.dao.AccountDao;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Data
//@NoArgsConstructor
//public class AccountDto {
//
//	private String email;
//	private String name;
//	private String password;
//	private String phone;
//	private String gender;
//	private String login;
//	
//	@Builder
//	public AccountDto(String email, String name, String password, String phone, String gender, String login) {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.phone = phone;
//        this.gender = gender;
//        this.login = login;
//    }
//	
//	 public AccountDao toEntity(){
//	        return AccountDao.builder()
//	        		.email(email)
//	        		.name(name)
//	        		//스프링 시큐리티 패스워드 암호화 처리
//	        		.password(new BCryptPasswordEncoder().encode(password))
//	        		.phone(phone)
//	        		.gender(gender)
//	        		.login(login)
//	        		.build();
//	 }        		
//	
//}
