package lee.hawoob.finalproject.dto;


import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.entity.Verse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Data
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class VerseDto extends Verse {

    private Long index;

    private String isbn;

    private User user;

    private String nickname;

    private String verse;

    private String sub;

    private int page;
}
