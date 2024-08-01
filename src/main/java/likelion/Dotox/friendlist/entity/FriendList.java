package likelion.Dotox.friendlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class FriendList implements Serializable {

    // pk
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty
    private int id;

    // 나의 아이디
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id1")
    @JsonIgnore
    @ApiModelProperty
    private Account id1;

    // 친구의 아이디
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id2")
    @JsonIgnore
    @ApiModelProperty
    private Account id2;

    // 나의 스크린타임
    @Column(name = "id1_screentime")
    private LocalDateTime id1ScreenTime;

    // 친구의 스크린타임
    @Column(name = "id2_screentime")
    private LocalDateTime id2ScreenTime;
}
