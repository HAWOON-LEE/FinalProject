package lee.hawoob.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BOARD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_INDEX")
    private Long boardIndex;

    @Column(name = "POST_TITLE")
    private String title;

//    ERD에 없음(파일첨부)
//    @Column(name = "IMG_URL")
//    private String imgURL;

    @Column(name = "POST_CONTENT")
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NICKNAME", referencedColumnName = "NICKNAME")
    private User user;



    @Column(name = "VIEW", columnDefinition = "integer default 0", nullable = false)
    private int view;

//    @OneToMany(mappedBy = "Board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private List<Comment> comments;

//    public void addComment(Comment comment){
//        commentList.add(comment);
//    }


    public void updateTitle(String title){
        this.title = title;
    }

    public void updateContent(String content){
        this.content = content;
    }

//    public void updateImgURL(String imgURL){
//        this.imgURL = imgURL;
//    }

    @Builder
    public Board(String title, User user, int view){
        this.title = title;
        this.user = user;
        this.view = view;
    }

}