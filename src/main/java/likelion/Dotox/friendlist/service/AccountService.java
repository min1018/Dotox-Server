package likelion.Dotox.friendlist.service;

import likelion.Dotox.friendlist.repository.AccountRepository;
import likelion.Dotox.friendlist.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AccountRepository accountRepository;

    /**
     * accountId를 기준으로 Account 엔티티를 조회하여 Optional 형태로 반환합니다.
     *
     * @param accountId 조회할 계정의 ID
     * @return Optional<Account> 조회된 Account 엔티티
     */
    public Optional<Account> findById(String accountId) {
        return accountRepository.findById(accountId);
    }

    /**
     * accountId에 해당하는 계정의 닉네임을 변경합니다.
     *
     * @param accountId 계정의 ID
     * @param nickName  변경할 닉네임
     */
    public void changeNickName(String accountId, String nickName) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            account.setNickName(nickName);
            accountRepository.save(account);
        }
    }

    /**
     * 닉네임을 포함하고 있으며 accountId와 다른 Account 목록을 검색하여 반환합니다.
     *
     * @param searching 검색할 닉네임
     * @param id        제외할 accountId
     * @return List<Account> 검색된 계정 목록
     */
    public List<Account> findByNickNameContainingAndAccountIdNot(String searching, String id) {
        return accountRepository.findByNickNameContainingAndAccountIdNot(searching, id);
    }
}