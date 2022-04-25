package lee.hawoob.finalproject.entity;

import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "BOARD")
@Data
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
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NICKNAME")
    private User user;

//    private LocalDateTime createDate;
//    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
//    private List<Comment> commentList = new ArrayList<>();

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

}