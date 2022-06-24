package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select m FROM User m where m.email = :email")
    User mypage(String email);

    //이메일 찾기
    User findByEmail(String email);

    //네이버 키값 찾기
    Optional<User> findByKeyID(String keyed);

    //닉네임 찾기
    User findByNickname(String nickname);

    //닉네임 중복 검사
    @Query("SELECT count (nickname) from User where nickname = :nickname")
    int nicknameCheck(String nickname);

    //MBTI 찾기
    User findByMbti(String mbti);

    //가입했던 로그인 정보 불러오기
    @Query("select email, keyID, nickname, mbti.mbti from User where keyID= keyID")
    String findAllByLogininfo(String keyID);

    //사용자 정보 업데이트
    @Modifying
    @Transactional
    @Query("update User m set m.mbti.mbti = :mbti, m.nickname = :nickname where m.email = :email")
    public void update(@Param("mbti")String mbti, @Param("nickname")String nickname,
                       @Param("email")String email);

    // 닉네임으로 MBTI 정보 찾기
    @Query(value = "select m.mbti.mbti from User m where m.nickname = :nickname")
    String findMbtidByNickname(String nickname);

}
