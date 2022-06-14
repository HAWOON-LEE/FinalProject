package lee.hawoob.finalproject.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER_INFO")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long user_id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "KEYID")
    private String keyID;

    @Column(name = "NICKNAME", nullable = false, unique = true)
    private String nickname;

    @JoinColumn(name = "MBTI")
    @OneToOne(fetch = FetchType.LAZY)
    private Mbti mbti;

    @CreationTimestamp
    @Column(name = "C_DATE")
    private Timestamp c_date;

    @Column(name = "ROLE")
    private String role;


    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User(String nickname, Mbti mbti) {
        this.nickname = nickname;
        this.mbti = mbti;
    }
    
}
