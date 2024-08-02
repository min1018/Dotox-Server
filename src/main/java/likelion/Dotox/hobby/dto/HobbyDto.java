package likelion.Dotox.hobby.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "사용자의 취미 정보를 업데이트하기 위한 DTO")
public class HobbyDto {

    @Schema(description = "사용자의 취미", example = "독서")
    private String hobby;

    @Schema(description = "사용자의 성별", example = "남성")
    private String gender;

    @Schema(description = "사용자의 나이", example = "30")
    private String age;
}
