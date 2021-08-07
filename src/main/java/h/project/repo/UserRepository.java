package h.project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import h.project.dao.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	  Optional<UserInfo> findByEmail(String email);

}
