package likelion.Dotox.friendlist.controller;

import likelion.Dotox.friendlist.service.FriendRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("friendrequest")
public class FriendRequestController {

    @Autowired
    private FriendRequestServiceImpl friendRequestService;

    /**
     * 요청 ID로 친구 요청 조회
     *
     * @param requestId 요청 ID
     * @return 요청 ID에 해당하는 친구 요청 목록을 계정 ID와 닉네임으로 묶어 List로 반환
     */
    @GetMapping("findFriendRequestByRequestId")
    public List<List<String>> findFriendRequestByRequestId(String requestId) {
        List<List<String>> result = friendRequestService.findFriendRequestByRequestId(requestId);
        System.out.println(result);
        System.out.println("요청확인용");
        return result;
    }

    /**
     * 요청 받은 ID로 친구 요청 조회
     *
     * @param requestedId 요청 받은 ID
     * @return 요청 받은 ID에 해당하는 친구 요청 목록을 계정 ID와 닉네임으로 묶어 List로 반환
     */
    @GetMapping("findFriendRequestByRequestedId")
    public List<List<String>> findFriendRequestByRequsetedId(String requestedId) {
        List<List<String>> result = friendRequestService.findFriendRequestByRequestedId(requestedId);
        System.out.println(requestedId);
        return result;
    }

    /**
     * 친구 요청 수락 처리
     *
     * @param id  친구 요청 ID
     * @param id1 요청 보낸 사용자의 ID
     * @param id2 요청 받은 사용자의 ID
     * @return 친구 요청 수락 결과 메시지 반환
     */
    @DeleteMapping("accept")
    public String accept(String id, String id1, String id2) {
        System.out.println("친구요청수락--------------------------------");
        String result = friendRequestService.accept(id, id1, id2);
        return result;
    }

    /**
     * 친구 요청 삭제 처리
     *
     * @param id 친구 요청 ID
     * @return 친구 요청 삭제 결과 메시지 반환
     */
    @DeleteMapping("delete")
    public String delete(String id) {
        System.out.println("친구요청삭제--------------------------------");
        String result = friendRequestService.delete(id);
        return result;
    }
}