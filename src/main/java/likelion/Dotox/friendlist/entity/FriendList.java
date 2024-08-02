package likelion.Dotox.friendlist.entity;

import likelion.Dotox.friendlist.entity.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "친구 목록 정보를 담는 엔티티")
public class FriendList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "친구 목록 ID", example = "1")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id1")
    @Schema(description = "나의 계정 정보")
    private Account id1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id2")
    @Schema(description = "친구의 계정 정보")
    private Account id2;

    @Column(name = "id1_screentime")
    @Schema(description = "나의 스크린 타임", example = "2024-08-02T11:59:04.122Z")
    private LocalDateTime id1ScreenTime;

    @Column(name = "id2_screentime")
    @Schema(description = "친구의 스크린 타임", example = "2024-08-02T11:59:04.122Z")
    private LocalDateTime id2ScreenTime;
}