package likelion.Dotox.friendlist.service;

import likelion.Dotox.friendlist.repository.AccountRepository;
import likelion.Dotox.friendlist.repository.FriendListRepository;
import likelion.Dotox.friendlist.repository.FriendRequestRepository;
import likelion.Dotox.friendlist.dto.AccountDTO;
import likelion.Dotox.friendlist.dto.FriendListDTO;
import likelion.Dotox.friendlist.dto.FriendRequestDTO;
import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.friendlist.entity.FriendList;
import likelion.Dotox.friendlist.entity.FriendRequest;
import likelion.Dotox.friendlist.service.FriendListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendListServiceImpl implements FriendListService {

    @Autowired
    private FriendListRepository friendListRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    /**
     * 모든 친구 목록을 조회합니다.
     *
     * @return ArrayList<FriendListDTO> 모든 친구 목록의 DTO 리스트
     */
    @Override
    public ArrayList<FriendListDTO> findAll() {
        Iterable<FriendList> result = friendListRepository.findAll();
        ArrayList<FriendListDTO> friendListDTOs = new ArrayList<>();

        for (FriendList friend : result) {
            FriendListDTO friendDTO = modelMapper.map(friend, FriendListDTO.class);
            friendListDTOs.add(friendDTO);
        }
        return friendListDTOs;
    }

    /**
     * 주어진 id1에 해당하는 사용자의 친구 목록을 조회합니다.
     *
     * @param id1 사용자의 ID
     * @return List<List<String>> id1의 친구 목록 (친구의 accountId와 nickName으로 구성된 리스트)
     */
    @Override
    public List<List<String>> findFriendListById1(String id1) {
        return friendListRepository.findId2ById1AccountId(id1);
    }

    /**
     * 주어진 id1과 id2에 해당하는 친구 관계를 삭제합니다.
     *
     * @param id1 사용자의 ID
     * @param id2 친구의 ID
     * @return String 삭제 결과 메시지
     */
    @Override
    public String delete(String id1, String id2) {
        try {
            Optional<FriendList> result1 = friendListRepository.findMyFunction(id1, id2);
            Optional<FriendList> result2 = friendListRepository.findMyFunction(id2, id1);

            result1.ifPresent(friendList -> friendListRepository.deleteById(friendList.getId()));
            result2.ifPresent(friendList -> friendListRepository.deleteById(friendList.getId()));

            return "삭제에 성공했습니다";
        } catch (Exception e) {
            e.printStackTrace();
            return "삭제 중 오류가 발생했습니다";
        }
    }

    /**
     * 주어진 id1과 id2에 해당하는 새로운 친구 관계를 추가합니다.
     *
     * @param id1 사용자의 ID
     * @param id2 친구의 ID
     * @return String 추가 결과 메시지
     */
    @Override
    public String post(String id1, String id2) {
        Optional<Account> account1 = accountRepository.findById(id1);
        Optional<Account> account2 = accountRepository.findById(id2);

        // 두 사용자 중 하나라도 존재하지 않으면 에러 메시지 반환
        if (account1.isEmpty() || account2.isEmpty()) {
            return "계정이 존재하지 않습니다";
        }

        // ModelMapper를 사용하여 AccountDTO로 변환
        AccountDTO accountDTO1 = modelMapper.map(account1.get(), AccountDTO.class);
        AccountDTO accountDTO2 = modelMapper.map(account2.get(), AccountDTO.class);

        // 이미 친구 관계가 존재하는지 확인
        Optional<FriendList> existingRelation = friendListRepository.findMyFunction(id1, id2);
        if (existingRelation.isPresent()) {
            return "이미 친구 상태입니다";
        } else {
            // FriendRequestDTO를 생성하여 FriendRequest 엔티티로 저장
            FriendRequestDTO friendRequestDTO = FriendRequestDTO.builder()
                    .requestId(accountDTO1)
                    .requestedId(accountDTO2)
                    .build();

            try {
                friendRequestRepository.save(modelMapper.map(friendRequestDTO, FriendRequest.class));
                return "친구 요청 성공";
            } catch (Exception e) {
                e.printStackTrace();
                return "친구 요청 중 오류가 발생했습니다";
            }
        }
    }

    /* 테스트 코드 - 추가 method */

    /**
     * 친구 관계를 추가하는 메서드입니다.
     *
     * @param id1 사용자의 ID
     * @param id2 친구의 ID
     */
    public void addFriend(String id1, String id2) {
        // 이미 친구 관계가 존재하는지 확인
        Optional<FriendList> existingRelation = friendListRepository.findMyFunction(id1, id2);

        // 친구 관계가 존재하지 않을 때만 추가
        if (existingRelation.isEmpty()) {
            // 두 사용자에 대한 Account 객체 생성
            Account account1 = new Account();
            account1.setAccountId(id1);
            Account account2 = new Account();
            account2.setAccountId(id2);

            // FriendList 엔티티 생성 및 설정
            FriendList friendList1 = new FriendList();
            friendList1.setId1(account1);
            friendList1.setId2(account2);

            // FriendList 저장
            friendListRepository.save(friendList1);

            // 양방향 친구 관계 설정
            FriendList friendList2 = new FriendList();
            friendList2.setId1(account2);
            friendList2.setId2(account1);

            friendListRepository.save(friendList2);
        }
    }

    /**
     * 친구 관계를 삭제하는 메서드입니다.
     *
     * @param id1 사용자의 ID
     * @param id2 친구의 ID
     */
    public void removeFriend(String id1, String id2) {
        // FriendList 엔티티 찾기
        Optional<FriendList> friendList1 = friendListRepository.findMyFunction(id1, id2);
        Optional<FriendList> friendList2 = friendListRepository.findMyFunction(id2, id1);

        // FriendList 엔티티 삭제
        friendList1.ifPresent(friendList -> friendListRepository.delete(friendList));
        friendList2.ifPresent(friendList -> friendListRepository.delete(friendList));
    }

    /**
     * 주어진 id1과 id2에 대한 친구 관계가 있는지 확인합니다.
     *
     * @param id1 사용자의 ID
     * @param id2 친구의 ID
     * @return boolean 친구 관계 여부 (true: 친구 관계 있음, false: 친구 관계 없음)
     */
    public boolean isFriend(String id1, String id2) {
        // 친구 관계가 존재하는지 확인
        Optional<FriendList> friendList1 = friendListRepository.findMyFunction(id1, id2);
        Optional<FriendList> friendList2 = friendListRepository.findMyFunction(id2, id1);

        return friendList1.isPresent() && friendList2.isPresent();
    }


    public List<AccountDTO> getFriendRanking(String accountId) {
        List<Account> friends = friendListRepository.findByAccount1OrderByScreenTimeDesc(accountId);

        return friends.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO convertToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountId(account.getAccountId());
        dto.setNickName(account.getNickName());
        dto.setScreenTime(account.getScreenTime());
        return dto;
    }
}