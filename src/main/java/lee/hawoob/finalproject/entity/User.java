package lee.hawoob.finalproject.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
    @OneToOne(fetch = FetchType.EAGER)
    private Mbti mbti;

    @CreationTimestamp
    @Column(name = "C_DATE")
    private Timestamp c_date;

    @Column(name = "ROLE")
    private String role;

    public User(String nickname, Mbti mbti) {
        this.nickname = nickname;
        this.mbti = mbti;
    }

    public static User createUser(){
        return User.builder()
                .user_id(1L)
                .email("")
                .nickname("")
                .build();
    }
}
