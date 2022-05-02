package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.dto.UserDto;
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

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //이메일 찾기
    User findByEmail(String email);

    //네이버 키값 찾기
    Optional<User> findByKeyID(String keyed);

    //닉네임 찾기
    User findByNickname(String nickname);

    User findByMbti(String mbti);

    //가입했던 로그인 정보 불러오기
    @Query("select email, keyID, nickname, mbti from User where keyID= keyID")
    String findAllByLogininfo(String keyID);

    //사용자 정보 업데이트
    @Modifying
    @Transactional
    @Query("update User m set m.mbti = :mbti, m.nickname = :nickname where m.email = :email")
    public void update(@Param("mbti")String mbti, @Param("nickname")String nickname,
                       @Param("email")String email);

}
