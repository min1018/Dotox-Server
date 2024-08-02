package likelion.Dotox.friendlist.entity;

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
public class FriendRequest implements Serializable {

    // 기본 키
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    // requestId와의 매핑: 요청 보낸 사용자의 계정
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private Account requestId;

    // requestedId와의 매핑: 요청 받은 사용자의 계정
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requested_id")
    @JsonIgnore
    private Account requestedId;
}
