package likelion.Dotox.friendlist.repository;

import likelion.Dotox.friendlist.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    // requestId를 기준으로 FriendRequest의 정보를 검색하여 요청자와 요청받은 사람의 accountId를 리스트로 반환합니다.
    @Query("SELECT fr.requestId.accountId, fr.requestedId.accountId FROM FriendRequest fr WHERE fr.requestId.accountId=:requestId")
    List<List<String>> findFriendRequestByRequestId(String requestId);

    // requestedId를 기준으로 FriendRequest의 정보를 검색하여 요청자와 요청받은 사람의 accountId와 요청 ID를 리스트로 반환합니다.
    @Query("SELECT fr.requestId.accountId, fr.requestedId.accountId, fr.id FROM FriendRequest fr WHERE fr.requestedId.accountId=:requestedId")
    List<List<String>> findFriendRequestByRequestedId(String requestedId);

    // requestId와 requestedId를 기준으로 FriendRequest를 검색하여 Optional 형태로 반환합니다.
    @Query("SELECT fr FROM FriendRequest fr WHERE fr.requestId.accountId=:id1 AND fr.requestedId.accountId=:id2")
    Optional<FriendRequest> findByRequestIdAccountIdAndRequestedIdAccountId(String id1, String id2);
}
