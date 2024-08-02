package likelion.Dotox.friendlist.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import likelion.Dotox.friendlist.entity.FriendList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// CustomFriendListRepository 인터페이스를 구현하는 구현체
@Repository
public class CustomFriendListRepositoryImpl implements CustomFriendListRepository {

    // EntityManager 주입
    @PersistenceContext
    private EntityManager entityManager;

    // id1의 계정에 대한 id2의 accountId를 검색하는 메서드
    @Override
    public List<List<String>> findId2ById1AccountId(String id1) {
        // JPQL 쿼리 작성
        String query = "SELECT fl.id, fl.id2.accountId, fl.id2.nickName FROM FriendList fl WHERE fl.id1.accountId = :id1";
        // 쿼리 실행 및 결과 받기
        List<Object[]> results = entityManager.createQuery(query, Object[].class)
                .setParameter("id1", id1)
                .getResultList();

        // 포맷된 결과를 담을 리스트 생성
        List<List<String>> formattedResults = new ArrayList<>();
        // 결과를 포맷하여 리스트에 추가
        for (Object[] result : results) {
            List<String> resultList = new ArrayList<>();
            for (Object obj : result) {
                resultList.add(obj != null ? obj.toString() : null);
            }
            formattedResults.add(resultList);
        }

        return formattedResults;
    }

    // 커스텀 기능을 수행하는 메서드, id1과 id2를 기반으로 FriendList를 검색하는 메서드
    @Override
    public Optional<FriendList> findMyFunction(String id1, String id2) {
        // JPQL 쿼리 작성
        String query = "SELECT fl FROM FriendList fl WHERE fl.id1.accountId = :id1 AND fl.id2.accountId = :id2";
        // 쿼리 실행 및 결과 받기
        List<FriendList> result = entityManager.createQuery(query, FriendList.class)
                .setParameter("id1", id1)
                .setParameter("id2", id2)
                .getResultList();
        // Optional로 결과 반환
        return result.stream().findFirst();
    }

    // id1의 accountId와 id2의 accountId를 기반으로 FriendList를 삭제하는 메서드
    @Override
    public void deleteById1AccountIdAndId2AccountId(String id1, String id2) {
        // JPQL 쿼리 작성
        String query = "DELETE FROM FriendList fl WHERE fl.id1.accountId = :id1 AND fl.id2.accountId = :id2";
        // 쿼리 실행
        entityManager.createQuery(query)
                .setParameter("id1", id1)
                .setParameter("id2", id2)
                .executeUpdate();
    }
}
