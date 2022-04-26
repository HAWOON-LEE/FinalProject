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

    @Column(name = "NICKNAME", nullable = false, unique = true)
    private String nickname;

    @JoinColumn(name = "MBTI_NAME")
    @OneToOne(fetch = FetchType.EAGER)
    private Mbti mbti;

    @CreationTimestamp
    @Column(name = "C_DATE")
    private Timestamp c_date;

    private String role;

    public User(String nickname, Mbti mbti) {
        this.nickname = nickname;
        this.mbti = mbti;
    }
}
