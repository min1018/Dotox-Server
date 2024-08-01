package likelion.Dotox.hobby.repository;

import likelion.Dotox.friendlist.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HobbyRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountId(String id);
}
