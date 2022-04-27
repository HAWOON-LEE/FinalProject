package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.dto.UserDto;
import lee.hawoob.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    Optional<User> findByKeyID(String keyed);

    @Query("select email, keyID, nickname, mbti from User where keyID= keyID")
    String findAllByLogininfo(String keyID);

    @Modifying
    @Transactional
    @Query("update User m set m.mbti.name = :mbti, m.nickname = :nickname where m.email = :email")
    public void update(@Param("mbti")String mbti, @Param("nickname")String nickname,
                       @Param("email")String email);
}
