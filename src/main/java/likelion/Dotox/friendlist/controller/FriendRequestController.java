package likelion.Dotox.friendlist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.Dotox.friendlist.service.FriendRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/friendrequest")
@Tag(name = "FriendRequest", description = "친구 요청 API")
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
    @Operation(summary = "요청 ID로 친구 요청 조회", description = "특정 요청 ID에 해당하는 친구 요청 목록을 조회합니다.")
    public List<List<String>> findFriendRequestByRequestId(
            @Parameter(description = "요청 ID", required = true) String requestId) {
        List<List<String>> result = friendRequestService.findFriendRequestByRequestId(requestId);
        return result;
    }

    /**
     * 요청 받은 ID로 친구 요청 조회
     *
     * @param requestedId 요청 받은 ID
     * @return 요청 받은 ID에 해당하는 친구 요청 목록을 계정 ID와 닉네임으로 묶어 List로 반환
     */
    @GetMapping("findFriendRequestByRequestedId")
    @Operation(summary = "요청 받은 ID로 친구 요청 조회", description = "특정 요청 받은 ID에 해당하는 친구 요청 목록을 조회합니다.")
    public List<List<String>> findFriendRequestByRequestedId(
            @Parameter(description = "요청 받은 ID", required = true) String requestedId) {
        List<List<String>> result = friendRequestService.findFriendRequestByRequestedId(requestedId);
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
    @Operation(summary = "친구 요청 수락", description = "특정 친구 요청을 수락하여 친구 목록에 추가합니다.")
    public String accept(
            @Parameter(description = "친구 요청 ID", required = true) String id,
            @Parameter(description = "요청 보낸 사용자 ID", required = true) String id1,
            @Parameter(description = "요청 받은 사용자 ID", required = true) String id2) {
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
    @Operation(summary = "친구 요청 삭제", description = "특정 친구 요청을 삭제합니다.")
    public String delete(
            @Parameter(description = "친구 요청 ID", required = true) String id) {
        String result = friendRequestService.delete(id);
        return result;
    }
}