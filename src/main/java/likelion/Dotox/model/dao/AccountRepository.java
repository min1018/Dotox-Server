package likelion.Dotox.model.dao;

import likelion.Dotox.entity.UserEntity;
import likelion.Dotox.model.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, String>{

    public List<Account> findByNickNameContainingAndAccountIdNot(String searching,String id);
    Account findByAccountId(String accountId);
}
