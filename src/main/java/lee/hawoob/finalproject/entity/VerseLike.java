package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VERSE_LIKE")
@Data
public class VerseLike implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VERSE_INDEX")
    private Verse verse;

    @Column(name = "COUNT")
    private int count;
    
}
