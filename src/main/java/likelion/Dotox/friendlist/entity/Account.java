package likelion.Dotox.friendlist.entity;

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

    // 닉네임
    @Column(name = "nick_name")
    private String nickName;

    // 이메일
    @Column(name = "email")
    private String email;

    // 취미
    @Column(name = "hobby")
    private String hobby;

    // 성별
    @Column(name = "gender")
    private String gender;

    // 나이
    @Column(name = "age")
    private String age;

    // 스크린타임
    @Column(name = "screen_time")
    private Long screenTime;

    // id1로 매핑된 친구 목록
    @OneToMany(mappedBy = "id1")
    private List<FriendList> friendlist1 = new ArrayList<>();

    // id2로 매핑된 친구 목록
    @OneToMany(mappedBy = "id2")
    private List<FriendList> friendlist2 = new ArrayList<>();

    // requestId로 매핑된 친구 요청 목록
    @OneToMany(mappedBy = "requestId")
    private List<FriendRequest> friendRequest1 = new ArrayList<>();

    // requestedId로 매핑된 친구 요청 목록
    @OneToMany(mappedBy = "requestedId")
    private List<FriendRequest> friendRequest2 = new ArrayList<>();
}