package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Mbti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiRepository extends JpaRepository<Mbti, String> {

}
