package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="REVIEW_LIKE")
@Data
public class ReviewLike implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_INDEX")
    private Review review;

    @Column(name = "COUNT")
    private int count;
    
}
