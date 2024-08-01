package likelion.Dotox.mypage.controller;

import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/{id}/myPage")
    public String loadUserInfo(@PathVariable String id) {
        System.out.println(id);
        Account account = myPageService.loadMyPage(id);
        if (account != null) {
            System.out.println(account.getAccountId());
        } else {
            System.out.println("Account not found");
        }
        return account.getAccountId();
    }
}
