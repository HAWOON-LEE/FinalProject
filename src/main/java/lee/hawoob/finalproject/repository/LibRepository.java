package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.LibId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LibRepository extends JpaRepository<Lib, LibId> {

    // 내 서재에 저장된 수를 기준으로 내림차순 정렬
    @Query(value = "select b from Lib b group by b.libId.isbn order by count(b.libId.isbn) desc")
    List<Lib>findTopBook();

    // 내 서재에 담긴 수 가져오기
    @Query(value = "select count(b) from Lib b group by b.libId.isbn having b.libId.isbn = :isbn")
    Integer countBook(String isbn);

    // 내 서재에 도서를 담은 회원의 닉네임을 불러오기
    @Query(value = "select b.user.nickname from Lib b")
    List<String> findAllNickname();

}