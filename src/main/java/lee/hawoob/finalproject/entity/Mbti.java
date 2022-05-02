package lee.hawoob.finalproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MBTI")
@Data
public class Mbti {

    @Id
    @Column
    private String mbti;

}
