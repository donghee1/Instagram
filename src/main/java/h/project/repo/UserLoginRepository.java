package h.project.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import h.project.dao.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin , String>  {
	//JpaRepository<> 상속받아 Repository 생성, UserDetailService에서 userEmail로 회원을 검색할 수 있도록 메서드를 정의
	  Optional<UserLogin> findByuserEmail(String userEmail); //인스타 자체 로그인을 위해
}

