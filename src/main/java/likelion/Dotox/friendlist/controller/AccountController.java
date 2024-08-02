package likelion.Dotox.friendlist.controller;

import jakarta.servlet.http.HttpSession;
import likelion.Dotox.friendlist.dto.AccountDTO;
import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.friendlist.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * 사용자 검색 기능 구현
     *
     * @param session 현재 세션 정보
     * @param searching 검색어
     * @param id 현재 로그인된 사용자의 계정 ID
     * @return 사용자 검색 결과를 계정 ID와 닉네임으로 묶어 List로 반환
     */
    @ResponseBody
    @GetMapping("searchUser")
    public List<List<String>> searchUser(HttpSession session, String searching, String id) {
        System.out.println(searching);

        // 세션에서 현재 로그인된 사용자의 계정 ID 가져오기
        id = session.getAttribute("accountId").toString();
        System.out.println(id);

        // 사용자 검색 결과를 담을 리스트 초기화
        List<Account> result;
        // 사용자 검색 수행
        result = accountService.findByNickNameContainingAndAccountIdNot(searching, id);
        List<List<String>> result2 = new ArrayList<>();

        // 검색 결과를 AccountDTO로 변환하고 리스트에 담기
        for (Account i : result) {
            AccountDTO j = modelMapper.map(i, AccountDTO.class);
            List<String> temp = new ArrayList<>();
            temp.add(j.getAccountId());
            temp.add(j.getNickName());
            result2.add(temp);
        }

        // 검색 결과 반환
        System.out.println(result2);
        System.out.println("성공확인용");
        return result2;
    }

    /**
     * 세션 정보 가져오기
     *
     * @param session 현재 세션 정보
     * @return 현재 세션에 저장된 계정 ID와 닉네임을 문자열 배열로 반환
     */
    @ResponseBody
    @PostMapping("/getsession")
    public String[] getSession(HttpSession session) {
        return new String[] {
                session.getAttribute("accountId").toString(),
                session.getAttribute("nickName").toString()
        };
    }

}