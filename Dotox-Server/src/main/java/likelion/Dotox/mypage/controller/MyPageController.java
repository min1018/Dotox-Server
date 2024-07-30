package likelion.Dotox.mypage.controller;

import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.mypage.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class MyPageController {
    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/{id}/mypage")
    public Account loadUserInfo(@PathVariable("id") String id) {
        return myPageService.loadMyPage(id);
    }
}