package likelion.Dotox.friendlist.repository;

import likelion.Dotox.friendlist.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, String>{

    public List<Account> findByNickNameContainingAndAccountIdNot(String searching,String id);
    Account findByAccountId(String accountId);
}
