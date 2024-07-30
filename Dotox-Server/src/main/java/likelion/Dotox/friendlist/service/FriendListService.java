package likelion.Dotox.friendlist.service;

import likelion.Dotox.friendlist.dto.FriendListDTO;

import java.util.ArrayList;
import java.util.List;

public interface FriendListService {
    ArrayList<FriendListDTO> findAll();

    List<List<String>> findFriendListById1(String id1);

    public String delete(String id1, String id2);

    public String post(String id1, String id2);
}