package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Book;
import lee.hawoob.finalproject.entity.Lib;
//import lee.hawoob.finalproject.entity.LibId;
import lee.hawoob.finalproject.entity.LibId;
import lee.hawoob.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibRepository extends JpaRepository<Lib, LibId> {

//    @Query(value = "select l.isbn from Lib as l where l.nickname.nickname = :NICKNAME")
//    List<Lib> selectByNickname(String nickname);


}
