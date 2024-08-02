package likelion.Dotox.friendlist.repository;

import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.friendlist.entity.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long>, CustomFriendListRepository {

    // id1의 계정에 연결된 id2의 accountId와 nickName을 검색하는 메서드
    @Query("SELECT fl.id2.accountId, fl.id2.nickName FROM FriendList fl WHERE fl.id1.accountId = :id1")
    List<List<String>> findId2ById1AccountId(@Param("id1") String id1);

    // id1과 id2를 기반으로 FriendList를 검색하는 메서드
    @Query("SELECT fl FROM FriendList fl WHERE fl.id1.accountId = :id1 AND fl.id2.accountId = :id2")
    Optional<FriendList> findMyFunction(String id1, String id2);

    // accountId의 친구 계정을 스크린타임 기준으로 정렬하는 메서드
    @Query("SELECT fl.id2 FROM FriendList fl WHERE fl.id1.accountId = :accountId ORDER BY fl.id2.screenTime DESC")
    List<Account> findByAccount1OrderByScreenTimeDesc(@Param("accountId") String accountId);

    // id1의 accountId와 id2의 accountId를 기반으로 FriendList를 삭제하는 메서드
    void deleteById1AccountIdAndId2AccountId(String id1, String id2);

}
