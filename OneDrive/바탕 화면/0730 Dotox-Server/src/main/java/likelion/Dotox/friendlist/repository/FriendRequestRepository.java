package likelion.Dotox.friendlist.repository;

import likelion.Dotox.friendlist.entity.FriendRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {
    @Query("SELECT fr.requestId.accountId, fr.requestedId.accountId FROM FriendRequest fr WHERE fr.requestId.accountId=:requestId")
    public abstract List<List<String>> findFriendRequestByRequestId(String requestId);

    @Query("SELECT fr.requestId.accountId, fr.requestId.nickName, fr.id FROM FriendRequest fr WHERE fr.requestedId.accountId=:requestedId")
    public abstract List<List<String>> findFriendRequestByRequestedId(String requestedId);

    /*
     * public abstract void deleteByRequestedIdAccountIdAndRequestIdAccountId(String
     * id1, String id2);
     */

    public abstract List<FriendRequest> findByRequestIdAccountIdAndRequestedIdAccountId(String id1, String id2);


}
