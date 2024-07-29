package likelion.Dotox.service;

import likelion.Dotox.model.dto.FriendListDTO;

import java.util.ArrayList;
import java.util.List;

public interface FriendListService {
    ArrayList<FriendListDTO> findAll();

    List<List<String>> findFriendListById1(String id1);

    public String delete(String id1, String id2);

    public String post(String id1, String id2);
}
