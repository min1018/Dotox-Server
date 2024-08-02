package likelion.Dotox.friendlist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "사용자 계정 정보를 나타내는 엔티티")
public class Account {

    @Id
    @Column(name = "account_id")
    @Schema(description = "사용자의 고유 ID", example = "user123")
    private String accountId;

    @Column(name = "nick_name")
    @Schema(description = "사용자의 닉네임", example = "John")
    private String nickName;

    @Column(name = "email")
    @Schema(description = "사용자의 이메일 주소", example = "john@example.com")
    private String email;

    @Column(name = "hobby")
    @Schema(description = "사용자의 취미", example = "독서")
    private String hobby;

    @Column(name = "gender")
    @Schema(description = "사용자의 성별", example = "남성")
    private String gender;

    @Column(name = "age")
    @Schema(description = "사용자의 나이", example = "30")
    private String age;

    @Column(name = "time")
    @Schema(description = "사용자가 활동한 시간", example = "120")
    private int time;

    @OneToMany(mappedBy = "id1")
    @Schema(description = "사용자와 관련된 친구 목록1")
    private List<FriendList> friendlist1 = new ArrayList<>();

    @OneToMany(mappedBy = "id2")
    @Schema(description = "사용자와 관련된 친구 목록2")
    private List<FriendList> friendlist2 = new ArrayList<>();

    @OneToMany(mappedBy = "requestId")
    @Schema(description = "사용자와 관련된 친구 요청 목록1")
    private List<FriendRequest> friendRequest1 = new ArrayList<>();

    @OneToMany(mappedBy = "requestedId")
    @Schema(description = "사용자와 관련된 친구 요청 목록2")
    private List<FriendRequest> friendRequest2 = new ArrayList<>();
}