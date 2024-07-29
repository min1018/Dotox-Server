package likelion.Dotox.service;

import likelion.Dotox.model.dao.AccountRepository;
import likelion.Dotox.model.dao.FriendListRepository;
import likelion.Dotox.model.dao.FriendRequestRepository;
import likelion.Dotox.model.dto.AccountDTO;
import likelion.Dotox.model.dto.FriendListDTO;
import likelion.Dotox.model.entity.Account;
import likelion.Dotox.model.entity.FriendList;
import likelion.Dotox.model.entity.FriendRequest;
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

    public List<List<String>> findFriendRequestByRequestId(String requestId) {
        List<List<String>> result = null;
        result = friendRequestRepository.findFriendRequestByRequestId(requestId);
        return result;
    }

    public List<List<String>> findFriendRequestByRequestedId(String requestedId) {
        List<List<String>> result = null;
        result = friendRequestRepository.findFriendRequestByRequestedId(requestedId);
        return result;
    }

    public String accept(String id, String id1, String id2) {
        try {
            Integer a = null;
            a = Integer.parseInt(id);
            friendRequestRepository.deleteById(a);
            /*
             * friendRequestRepository.deleteByRequestedIdAccountIdAndRequestIdAccountId(id1
             * ,id2); No EntityManager with actual transaction available for current thread
             * - cannot reliably process 'remove' call; nested exception is
             * javax.persistence.TransactionRequiredException: No EntityManager with actual
             * transaction available for current thread - cannot reliably process 'remove'
             * call
             */
            List<FriendRequest> aaa = friendRequestRepository.findByRequestIdAccountIdAndRequestedIdAccountId(id2, id1);
            if (aaa.size()!=0) {
                friendRequestRepository.deleteById(aaa.get(0).getId());
            }


            Optional<Account> result1 = accountRepository.findById(id1);
            Optional<Account> result2 = accountRepository.findById(id2);
            AccountDTO result3 = null;
            AccountDTO result4 = null;
            if (result1.isPresent()) {
                result3 = modelMapper.map(result1.get(), AccountDTO.class);
            }
            if (result2.isPresent()) {
                result4 = modelMapper.map(result2.get(), AccountDTO.class);
            }
            FriendListDTO result = FriendListDTO.builder().id1(result3).id2(result4).build();
            friendListRepository.save(modelMapper.map(result, FriendList.class));
            FriendListDTO resultt = FriendListDTO.builder().id2(result3).id1(result4).build();
            friendListRepository.save(modelMapper.map(resultt, FriendList.class));
            return "수락요청";

        } catch (Exception e) {
            e.printStackTrace();
            return "수락하는 과정 중 오류가 발생했습니다";
        }
    }

    public String delete(String id) {
        try {
            Integer id2 = null;
            id2 = Integer.parseInt(id);
            friendRequestRepository.deleteById(id2);
            return "수락삭제완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "수락삭제하는 과정 중 오류가 발생했습니다";
        }
    }

//	public String save(AccountDTO requestId, AccountDTO requestedId) {
//		System.out.println(requestId);
//		try {
//			Account account1 = modelMapper.map(requestId, Account.class);
//			Account account2 = modelMapper.map(requestedId, Account.class);
//			System.out.println(account1);
////			FriendRequest friendRequest = new FriendRequest();
////			friendRequest.setRequestId(account1);
////			friendRequest.setRequestedId(account2);
//			FriendRequest friendRequest = modelMapper.map(requestedId, FriendRequest.class);
//			System.out.println(friendRequest);
//			friendRequestRepository.save(friendRequest);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "친구 신청에 실패했습니다";
//		}
//		return "친구 신청을 보냈습니다";
//	}

}
