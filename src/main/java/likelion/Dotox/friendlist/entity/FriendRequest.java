package likelion.Dotox.friendlist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.Dotox.friendlist.entity.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "친구 요청 정보를 담는 엔티티")
public class FriendRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "친구 요청 ID", example = "1")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    @Schema(description = "요청 보낸 사용자의 계정 정보")
    private Account requestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requested_id")
    @Schema(description = "요청 받은 사용자의 계정 정보")
    private Account requestedId;
}