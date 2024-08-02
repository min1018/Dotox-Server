package likelion.Dotox.friendlist.service;

import likelion.Dotox.friendlist.repository.AccountRepository;
import likelion.Dotox.friendlist.repository.FriendListRepository;
import likelion.Dotox.friendlist.repository.FriendRequestRepository;
import likelion.Dotox.friendlist.dto.AccountDTO;
import likelion.Dotox.friendlist.dto.FriendListDTO;
import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.friendlist.entity.FriendList;
import likelion.Dotox.friendlist.entity.FriendRequest;
import likelion.Dotox.friendlist.service.FriendRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private FriendListRepository friendListRepository;
    @Autowired
    private AccountRepository accountRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    /**
     * 주어진 requestId에 해당하는 친구 요청 목록을 조회합니다.
     *
     * @param requestId 요청을 보낸 사용자의 ID
     * @return List<List<String>> 요청을 받은 사용자의 ID와 요청자의 ID로 구성된 리스트
     */
    public List<List<String>> findFriendRequestByRequestId(String requestId) {
        return friendRequestRepository.findFriendRequestByRequestId(requestId);
    }

    /**
     * 주어진 requestedId에 해당하는 친구 요청 목록을 조회합니다.
     *
     * @param requestedId 요청을 받은 사용자의 ID
     * @return List<List<String>> 요청을 보낸 사용자의 ID와 요청자의 ID로 구성된 리스트
     */
    public List<List<String>> findFriendRequestByRequestedId(String requestedId) {
        return friendRequestRepository.findFriendRequestByRequestedId(requestedId);
    }

    /**
     * 주어진 requestId와 requestedId로 새로운 친구 요청을 저장합니다.
     *
     * @param requestId   요청을 보낸 사용자의 ID
     * @param requestedId 요청을 받은 사용자의 ID
     * @return String 저장 결과 메시지
     */
    public String save(String requestId, String requestedId) {
        try {
            Optional<Account> account1 = accountRepository.findById(requestId);
            Optional<Account> account2 = accountRepository.findById(requestedId);

            if (account1.isPresent() && account2.isPresent()) {
                FriendRequest friendRequest = new FriendRequest();
                friendRequest.setRequestId(account1.get());
                friendRequest.setRequestedId(account2.get());
                friendRequestRepository.save(friendRequest);
                return "친구 신청을 보냈습니다";
            } else {
                return "계정이 존재하지 않습니다";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "친구 신청에 실패했습니다";
        }
    }

    /**
     * 주어진 requestId로 친구 요청을 수락합니다.
     *
     * @param id  요청 ID
     * @param id1 요청을 받은 사용자의 ID
     * @param id2 요청을 보낸 사용자의 ID
     * @return String 수락 결과 메시지
     */
    public String accept(String id, String id1, String id2) {
        try {
            Long requestId = Long.parseLong(id);
            friendRequestRepository.deleteById(requestId);

            Optional<Account> account1 = accountRepository.findById(id1);
            Optional<Account> account2 = accountRepository.findById(id2);

            if (account1.isPresent() && account2.isPresent()) {
                FriendList friendList1 = new FriendList();
                friendList1.setId1(account1.get());
                friendList1.setId2(account2.get());

                FriendList friendList2 = new FriendList();
                friendList2.setId1(account2.get());
                friendList2.setId2(account1.get());

                friendListRepository.save(friendList1);
                friendListRepository.save(friendList2);

                return "수락요청";
            } else {
                return "계정이 존재하지 않습니다";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "수락하는 과정 중 오류가 발생했습니다";
        }
    }

    /**
     * 주어진 requestId로 친구 요청을 삭제합니다.
     *
     * @param id 요청 ID
     * @return String 삭제 결과 메시지
     */
    public String delete(String id) {
        try {
            Long requestId = Long.parseLong(id);
            friendRequestRepository.deleteById(requestId);
            return "수락삭제완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "수락삭제하는 과정 중 오류가 발생했습니다";
        }
    }
}
