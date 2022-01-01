package h.project.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Lombok 라이브러리에서 제공하는 어노테이션
//JPA에서 프록시를 생성을 위해 기본 생성자를 반드시 하나 생성 해야함.
//테스트를 위해서 임시 Public --> 생성산 코드는 PROTECTED 접근권한 줘야 함.
//객체 생성 시 안정성 어느정도 보장
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// 객체와 테이블 매핑 (JPA가 관리)
@Entity

//Getter 메소드를 생성해줌.
@Data
@Table(name = "USER")
public class UserInfo implements UserDetails {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no" )
	private Long sid;
	
	@Column(name = "user_email", unique = true)
	private String email;
	
			
	@Column(name = "user_name" )
	private String name;
	@Column(name = "user_password" )
	private String password;
	@Column(name = "user_phone" )
	private String phone;
	@Column(name = "user_gender" )
	private String gender;
	@Column(name = "user_login" )
	private String login;

//    @Column(name = "auth")
//    private String auth;
  
  @Builder
	public UserInfo(String name, String password, String email, String phone, String gender, String login) {
      this.name = name;
      this.password = password;
      this.email = email;
      this.phone = phone;
      this.gender = gender;
      this.login = login;
  }

  // 사용자의 권한을 콜렉션 형태로 반환
  // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    Set<GrantedAuthority> roles = new HashSet<>();
//    for (String role : auth.split(",")) {
//      roles.add(new SimpleGrantedAuthority(role));
//    }
//    return roles;
//  }

  // 사용자의 id를 반환 (unique한 값)
  @Override
  public String getUsername() {
    return email;
  }

  // 사용자의 password를 반환
  @Override
  public String getPassword() {
    return password;
  }

  // 계정 만료 여부 반환
  @Override
  public boolean isAccountNonExpired() {
    // 만료되었는지 확인하는 로직
    return true; // true -> 만료되지 않았음
  }

  // 계정 잠금 여부 반환
  @Override
  public boolean isAccountNonLocked() {
    // 계정 잠금되었는지 확인하는 로직
    return true; // true -> 잠금되지 않았음
  }

  // 패스워드의 만료 여부 반환
  @Override
  public boolean isCredentialsNonExpired() {
    // 패스워드가 만료되었는지 확인하는 로직
    return true; // true -> 만료되지 않았음
  }

  // 계정 사용 가능 여부 반환
  @Override
  public boolean isEnabled() {
    // 계정이 사용 가능한지 확인하는 로직
    return true; // true -> 사용 가능
  }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
}
}