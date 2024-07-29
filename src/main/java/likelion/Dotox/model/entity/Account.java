package likelion.Dotox.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Account {

    @Id
    @Column(name = "account_id")
    private String accountId;
    private String pw;
    @Column(name = "hash_salt")
    private String hashSalt;
    @Column(name = "pw_question")
    private String pwQuestion;
    @Column(name = "nick_name")
    private String nickName;

    @OneToMany(mappedBy = "id1")
    private List<FriendList> friendlist1 = new ArrayList<FriendList>();

    @OneToMany(mappedBy = "id2")
    private List<FriendList> friendlist2 = new ArrayList<FriendList>();

    @OneToMany(mappedBy = "requestId")
    private List<FriendRequest> friendRequest1 = new ArrayList<FriendRequest>();

    @OneToMany(mappedBy = "requestedId")
    private List<FriendRequest> friendRequest2 = new ArrayList<FriendRequest>();

//    @OneToMany(mappedBy = "accountId")
//    private List<Preference> preference = new ArrayList<Preference>();

}
