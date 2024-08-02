package likelion.Dotox.friendlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "친구 목록 DTO")
public class FriendListDTO {

    @Schema(description = "친구 목록 ID", example = "1")
    private int id;

    @Schema(description = "사용자 1의 계정 정보")
    private AccountDTO id1;

    @Schema(description = "사용자 2의 계정 정보")
    private AccountDTO id2;
}
