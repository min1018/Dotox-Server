package likelion.likelion6.repositorty;

import likelion.likelion6.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
