package likelion.Dotox.hobby.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HobbyDto {
    private String hobby;

    private String gender;

    private String age;
}
