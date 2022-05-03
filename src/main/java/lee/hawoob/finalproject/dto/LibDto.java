package lee.hawoob.finalproject.dto;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class LibDto {

    private Book book;

    private User user;

}
