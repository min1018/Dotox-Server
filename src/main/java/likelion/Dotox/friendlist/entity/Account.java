package likelion.Dotox.friendlist.entity;

import likelion.Dotox.friendlist.entity.FriendList;
import likelion.Dotox.friendlist.entity.FriendRequest;

import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@Schema(description = "계정 정보를 담는 엔티티")
public class Account {

    @Id
    @Column(name = "account_id")
    @Schema(description = "계정 ID", example = "user123")
    private String accountId;

    @Column(name = "nick_name")
    @Schema(description = "닉네임", example = "nickname")
    private String nickName;

    @Column(name = "email")
    @Schema(description = "이메일 주소", example = "user@example.com")
    private String email;

    @Column(name = "hobby")
    @Schema(description = "취미", example = "reading")
    private String hobby;

    @Column(name = "gender")
    @Schema(description = "성별", example = "male")
    private String gender;

    @Column(name = "age")
    @Schema(description = "나이", example = "30")
    private String age;

    @Column(name = "screen_time")
    @Schema(description = "스크린 타임", example = "3600")
    private Long screenTime;

    @OneToMany(mappedBy = "id1")
    @ArraySchema(schema = @Schema(description = "id1로 매핑된 친구 목록"))
    private List<FriendList> friendlist1 = new ArrayList<>();

    @OneToMany(mappedBy = "id2")
    @ArraySchema(schema = @Schema(description = "id2로 매핑된 친구 목록"))
    private List<FriendList> friendlist2 = new ArrayList<>();

    @OneToMany(mappedBy = "requestId")
    @ArraySchema(schema = @Schema(description = "requestId로 매핑된 친구 요청 목록"))
    private List<FriendRequest> friendRequest1 = new ArrayList<>();

    @OneToMany(mappedBy = "requestedId")
    @ArraySchema(schema = @Schema(description = "requestedId로 매핑된 친구 요청 목록"))
    private List<FriendRequest> friendRequest2 = new ArrayList<>();
}