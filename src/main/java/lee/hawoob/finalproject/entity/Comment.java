package lee.hawoob.finalproject.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Table(name="COMMENT")
@Getter
@Entity
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NICKNAME")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_INDEX")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @Column(nullable = false)
    private String content;

    private boolean isRemoved = false;

//  부모 댓글을 삭제해도 자식 댓글(대댓글)은 남아있음
    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();

//    @Builder
//    public Comment( User user, Board board, Comment parent, String content) {
//        this.user = user;
//        this.board = board;
//        this.parent = parent;
//        this.content = content;
//        this.isRemoved = false;
//    }
//
//
//
//    public List<Comment> findRemovableList() {
//
//        List<Comment> result = new ArrayList<>();
//
//        Optional.ofNullable(this.parent).ifPresentOrElse(
//
//                parentComment ->{//대댓글인 경우 (부모가 존재하는 경우)
//                    if( parentComment.isRemoved()&& parentComment.isAllChildRemoved()){
//                        result.addAll(parentComment.getChildList());
//                        result.add(parentComment);
//                    }
//                },
//
//                () -> {//댓글인 경우
//                    if (isAllChildRemoved()) {
//                        result.add(this);
//                        result.addAll(this.getChildList());
//                    }
//                }
//        );
//
//        return result;
//    }
//
//    private boolean isAllChildRemoved() {
//        return getChildList().stream()//https://kim-jong-hyun.tistory.com/110 킹종현님 사랑합니다.
//                .map(Comment::isRemoved)//지워졌는지 여부로 바꾼다
//                .filter(isRemove -> !isRemove)//지워졌으면 true, 안지워졌으면 false이다. 따라서 filter에 걸러지는 것은 false인 녀석들이고, 있다면 false를 없다면 orElse를 통해 true를 반환한다.
//                .findAny()//지워지지 않은게 하나라도 있다면 false를 반환
//                .orElse(true);//모두 지워졌다면 true를 반환
//
//    }
}
