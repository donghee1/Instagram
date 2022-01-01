package h.project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import h.project.dao.UserInfo;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<UserInfo, Long> {
	  Optional<UserInfo> findByEmail(String email);
}
