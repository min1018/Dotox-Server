package likelion.Dotox.mypage.service;

import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.friendlist.repository.AccountRepository;
import likelion.Dotox.mypage.repository.MyPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyPageService {
    private final MyPageRepository myPageRepository;

    public MyPageService(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    @Transactional
    public Account loadMyPage(String id) {
        return myPageRepository.findByAccountId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + id));
    }
}
