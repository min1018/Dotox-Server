package likelion.Dotox.friendlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FriendListDTO {

    private int id;         // 친구 목록 ID

    private AccountDTO id1; // 사용자 1의 계정 정보

    private AccountDTO id2; // 사용자 2의 계정 정보
}
