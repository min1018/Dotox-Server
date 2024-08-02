package likelion.Dotox.friendlist.controller;

import likelion.Dotox.friendlist.dto.AccountDTO;
import likelion.Dotox.friendlist.dto.FriendListDTO;
import likelion.Dotox.friendlist.service.FriendListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("friendlist")
public class FriendListController {

    @Autowired
    private FriendListServiceImpl friendListService;

    /**
     * 모든 친구 목록 조회
     *
     * @return 모든 친구 목록을 담은 ArrayList 반환
     */
    @GetMapping("findall")
    public ArrayList<FriendListDTO> findAll() {
        System.out.println("friendlist/findAll() 실행----------------------------------");
        ArrayList<FriendListDTO> result = friendListService.findAll();
        return result;
    }

    /**
     * 특정 사용자의 친구 목록 조회
     *
     * @param id1 사용자 ID
     * @return 사용자가 가지고 있는 친구 목록을 계정 ID와 닉네임으로 묶어 List로 반환
     */
    @GetMapping("findFriendListById1")
    public List<List<String>> findFriendListById1(String id1) {
        System.out.println("friendlist/findFriendListById1() 실행----------------------------------");
        List<List<String>> result = friendListService.findFriendListById1(id1);
        return result;
    }

    /**
     * 친구 목록에서 삭제
     *
     * @param id1 삭제할 사용자의 ID
     * @param id2 삭제할 친구의 ID
     * @return 삭제 성공 여부를 문자열로 반환
     */
    @DeleteMapping("delete")
    public String delete(String id1, String id2) {
        System.out.println("friendlist/delete() 실행----------------------------------");
        String result = friendListService.delete(id1, id2);
        return result;
    }

    /**
     * 친구 목록에 추가
     *
     * @param id1 사용자 ID
     * @param id2 추가할 친구의 ID
     * @return 친구 추가 결과에 따른 메시지 반환
     */
    @PostMapping("post")
    public String post(String id1, String id2) {
        System.out.println("friendlist/post() 실행----------------------------");
        String result = friendListService.post(id1, id2);

        // 친구 추가 결과에 따른 메시지 반환
        if (result.equals("친구상태")) {
            return "이미 친구 목록에 존재합니다";
        } else if (result.equals("친구신청이미보냄")) {
            return "이미 친구신청을 보냈습니다";
        }
        return "친구요청에 성공했습니다";
    }

    @GetMapping("/friends/ranking/{accountId}")
    public List<AccountDTO> getFriendRanking(@PathVariable String accountId) {
        return friendListService.getFriendRanking(accountId);
    }

}