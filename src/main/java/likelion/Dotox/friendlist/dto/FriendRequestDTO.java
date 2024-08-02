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
@Builder
@ToString
public class FriendRequestDTO {

    private int id;                 // 친구 요청 ID

    private AccountDTO requestId;   // 요청을 보낸 사용자의 계정 정보

    private AccountDTO requestedId; // 요청을 받은 사용자의 계정 정보
}
