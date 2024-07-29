package likelion.Dotox.friendlist.repository;

import likelion.Dotox.friendlist.entity.FriendList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendListRepository extends CrudRepository<FriendList, Integer> {

    @Query("SELECT fl.id, fl.id2.accountId, fl.id2.nickName FROM FriendList fl WHERE fl.id1.accountId=:id1")
    public abstract List<List<String>> findId2ById1AccountId(String id1);
//	public abstract List<List<String>> findIdAndId2AccountIdAndId2NickNameById1AccountId(String id1); 오류나는문장

    @Query("SELECT fl FROM FriendList fl WHERE fl.id1.accountId=:id1 AND fl.id2.accountId=:id2")
    public abstract Optional<FriendList> findMyFunction(String id1, String id2);
//	public abstract void deleteById1AccountIdAndId2AccountId(String id1, String id2); 오류나는문장

}
