package likelion.Dotox.friendlist.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FriendRequestService {

    /**
     * 주어진 requestId에 해당하는 친구 요청 목록을 조회합니다.
     *
     * @param requestId 요청을 보낸 사용자의 ID
     * @return List<List<String>> 요청을 받은 사용자의 ID와 요청자의 ID로 구성된 리스트
     */
    List<List<String>> findFriendRequestByRequestId(String requestId);

    /**
     * 주어진 requestedId에 해당하는 친구 요청 목록을 조회합니다.
     *
     * @param requestedId 요청을 받은 사용자의 ID
     * @return List<List<String>> 요청을 보낸 사용자의 ID와 요청자의 ID로 구성된 리스트
     */
    List<List<String>> findFriendRequestByRequestedId(String requestedId);

    /**
     * 주어진 requestId와 requestedId로 새로운 친구 요청을 저장합니다.
     *
     * @param requestId   요청을 보낸 사용자의 ID
     * @param requestedId 요청을 받은 사용자의 ID
     * @return String 저장 결과 메시지
     */
    String save(String requestId, String requestedId);

    /**
     * 주어진 requestId와 id1, id2로 친구 요청을 수락합니다.
     *
     * @param requestId 요청을 보낸 사용자의 ID
     * @param id1       요청을 받은 사용자의 ID
     * @param id2       요청을 보낸 사용자의 ID
     * @return String 수락 결과 메시지
     */
    String accept(String requestId, String id1, String id2);

    /**
     * 주어진 requestId로 친구 요청을 삭제합니다.
     *
     * @param requestId 요청을 보낸 사용자의 ID
     * @return String 삭제 결과 메시지
     */
    String delete(String requestId);
}
