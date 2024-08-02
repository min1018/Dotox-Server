package likelion.Dotox.friendlist.repository;

import likelion.Dotox.friendlist.entity.FriendList;

import java.util.List;
import java.util.Optional;

// CustomFriendListRepository 인터페이스
public interface CustomFriendListRepository {

    // id1의 계정에 대한 id2의 accountId를 검색하는 메서드
    List<List<String>> findId2ById1AccountId(String id1);

    // 커스텀 기능을 수행하는 메서드, id1과 id2를 기반으로 FriendList를 검색하는 메서드
    Optional<FriendList> findMyFunction(String id1, String id2);

    // id1의 accountId와 id2의 accountId를 기반으로 FriendList를 삭제하는 메서드
    void deleteById1AccountIdAndId2AccountId(String id1, String id2);
}