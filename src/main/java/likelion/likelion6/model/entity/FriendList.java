package likelion.likelion6.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class FriendList implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id1")
    @JsonIgnore
    @ApiModelProperty
    private Account id1;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id2")
    @JsonIgnore
    @ApiModelProperty
    private Account id2;


}
