package likelion.Dotox.friendlist.repository;

import likelion.Dotox.friendlist.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Account 엔티티를 다루는 CRUD 기능을 제공하는 Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    // 닉네임을 포함하고 accountId가 주어진 id와 다른 모든 계정을 검색하는 메서드
    public List<Account> findByNickNameContainingAndAccountIdNot(String searching, String id);

    // accountId를 기반으로 계정을 검색하는 메서드
    Account findByAccountId(String accountId);
}