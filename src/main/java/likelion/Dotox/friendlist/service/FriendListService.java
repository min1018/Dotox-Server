package likelion.Dotox.friendlist.service;

import likelion.Dotox.friendlist.dto.FriendListDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface FriendListService {

    /**
     * 모든 친구 목록을 조회합니다.
     *
     * @return ArrayList<FriendListDTO> 모든 친구 목록의 DTO 리스트
     */
    ArrayList<FriendListDTO> findAll();

    /**
     * 주어진 id1에 해당하는 사용자의 친구 목록을 조회합니다.
     *
     * @param id1 사용자의 ID
     * @return List<List<String>> id1의 친구 목록 (친구의 accountId와 nickName으로 구성된 리스트)
     */
    List<List<String>> findFriendListById1(String id1);

    /**
     * 주어진 id1과 id2에 해당하는 친구 관계를 삭제합니다.
     *
     * @param id1 사용자의 ID
     * @param id2 친구의 ID
     * @return String 삭제 결과 메시지
     */
    String delete(String id1, String id2);

    /**
     * 주어진 id1과 id2에 해당하는 새로운 친구 관계를 추가합니다.
     *
     * @param id1 사용자의 ID
     * @param id2 친구의 ID
     * @return String 추가 결과 메시지
     */
    String post(String id1, String id2);
}
