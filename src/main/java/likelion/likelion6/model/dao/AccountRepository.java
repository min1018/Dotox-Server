package likelion.likelion6.model.dao;

import likelion.likelion6.model.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, String>{

    public List<Account> findByNickNameContainingAndAccountIdNot(String searching,String id);

}
