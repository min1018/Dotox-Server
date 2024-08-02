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
public class AccountDTO {

    private String accountId;  // 계정 ID

    private String nickName;   // 닉네임

    private String email;      // 이메일

    private String hobby;      // 취미

    private String gender;     // 성별

    private String age;        // 나이

    private Long screenTime;   // 스크린타임
}
