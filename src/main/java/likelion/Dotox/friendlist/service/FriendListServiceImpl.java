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

@Service
public class FriendListServiceImpl implements FriendListService {
    @Autowired
    private FriendListRepository friendListRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public ArrayList<FriendListDTO> findAll() {
        Iterable<FriendList> result = friendListRepository.findAll();
        ArrayList<FriendListDTO> result2 = new ArrayList<FriendListDTO>();

        for (FriendList friend : result) {
            FriendListDTO friend2 = modelMapper.map(friend, FriendListDTO.class);
            result2.add(friend2);
            System.out.println(friend2);
        }
        return result2;
    }

    public List<List<String>> findFriendListById1(String id1) {
        List<List<String>> result = null;
        result = friendListRepository.findId2ById1AccountId(id1);
        System.out.println(result);
        return result;
    }

    public String delete(String id1, String id2) {
        try {
            Optional<FriendList> result = friendListRepository.findMyFunction(id1, id2);
            Optional<FriendList> result2 = friendListRepository.findMyFunction(id2, id1);
            Integer list1 = null;
            Integer list2 = null;
            if (result.isPresent()) {
                list1 = result.get().getId();
            }
            if (result2.isPresent()) {
                list2 = result2.get().getId();
            }
            friendListRepository.deleteById(list1);
            friendListRepository.deleteById(list2);

            return "삭제에 성공했습니다";
        } catch (Exception e) {
            e.printStackTrace();
            return "삭제 중 오류가 발생했습니다";
        }
    }

    public String post(String id1, String id2) {
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
        Optional<FriendList> result5 = friendListRepository.findMyFunction(id1, id2);
        System.out.println("SDFSDFSDFSDFSDFSD");
        System.out.println(result5);
        if (result5.isPresent()) {
            return "친구상태";
        } else {
            FriendRequestDTO result = FriendRequestDTO.builder().requestId(result3).requestedId(result4).build();
            try {
                friendRequestRepository.save(modelMapper.map(result, FriendRequest.class));
            } catch (Exception e) {
                e.printStackTrace();
                return "친구신청이미보냄";
            }
        }
        return "친구요청성공";
    }

}
