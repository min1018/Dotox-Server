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
@Schema(description = "계정 정보 DTO")
public class AccountDTO {

    @Schema(description = "계정 ID", example = "user123")
    private String accountId;

    @Schema(description = "닉네임", example = "John")
    private String nickName;

    @Schema(description = "이메일 주소", example = "john@example.com")
    private String email;

    @Schema(description = "취미", example = "reading")
    private String hobby;

    @Schema(description = "성별", example = "male")
    private String gender;

    @Schema(description = "나이", example = "30")
    private String age;

    @Schema(description = "스크린 타임", example = "3600")
    private Long screenTime;
}