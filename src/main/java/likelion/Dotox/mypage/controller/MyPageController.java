package likelion.Dotox.mypage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "MyPage", description = "사용자의 마이페이지 정보를 관리하는 엔드포인트")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/{id}/myPage")
    @Operation(
            summary = "사용자 정보 조회",
            description = "주어진 ID를 바탕으로 사용자의 정보를 조회합니다.",
            responses = {
                    @ApiResponse(description = "사용자 정보를 성공적으로 조회함", responseCode = "200"),
                    @ApiResponse(description = "사용자를 찾을 수 없음", responseCode = "404")
            }
    )
    public Account loadUserInfo(
            @Parameter(description = "조회할 사용자의 ID") @PathVariable("id") String id
    ){
        System.out.println(id);
        Account account = myPageService.loadMyPage(id);
        if (account != null) {
            System.out.println(account.getAccountId());
        } else {
            System.out.println("Account not found");
        }
        return account;
    }
}
