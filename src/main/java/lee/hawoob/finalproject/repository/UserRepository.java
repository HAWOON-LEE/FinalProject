package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User m set m.mbti.name = :mbti, m.nickname = :nickname where m.email = :email")
    public void update(@Param("mbti")String mbti, @Param("nickname")String nickname,
                       @Param("email")String email);

}
