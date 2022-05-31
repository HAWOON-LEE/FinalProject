package lee.hawoob.finalproject.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="COMMENT")
@Getter
@Setter
@Entity
public class Comment{
// extends BaseTimeEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "NICKNAME")
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_INDEX")
    private Board board;

//    private Long boardindex;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PARENT_ID")
//    private Comment parent;

    @Column(name = "REPLY", nullable = false)
    private String comment;

//    @Column(name = "CREATE_DATE")
//    @CreatedDate
//    private String createdDate;
//
//    @Column(name = "MODIFIED_DATE")
//    @LastModifiedDate
//    private String modifiedDate;

}
