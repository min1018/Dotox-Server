package likelion.Dotox.mypage.repository;

import likelion.Dotox.friendlist.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyPageRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountId(String id);
}
