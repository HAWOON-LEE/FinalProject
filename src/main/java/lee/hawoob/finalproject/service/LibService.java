package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.LibId;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.repository.LibRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class LibService {

    @Autowired
    private LibRepository libRepository;

    @Autowired
    private UserRepository userRepository;

    // 내 서재 목록 불러오기
    public List<Lib> findAllByNickname(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<Lib> isbns = libRepository.findAll();
        User user = userRepository.findByNickname(principalDetails.getUser().getNickname());

        List<Lib> libs = new ArrayList<>();
        for(int i=0; i < isbns.size(); i++) {
            if(isbns.get(i).getUser().getNickname() == user.getNickname()) {
                libs.add(isbns.get(i));
            }
        }

        return libs;
    }

    // 내 서재에 담기
    public void saveBook(String isbn, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        LibId libId = new LibId();
        libId.setIsbn(isbn);
        libId.setNickname(principalDetails.getUser().getNickname());

        Lib lib = new Lib();
        lib.setLibId(libId);

        libRepository.save(lib);
    }

    // 내 서재 보관 유무 검색
    public boolean searchLib(String isbn, @AuthenticationPrincipal PrincipalDetails principalDetails) {

       boolean result = true;

       String nickname = principalDetails.getUser().getNickname();

       Optional<Lib> obj = libRepository.findById(new LibId(isbn, nickname));

        if(obj.isPresent()) {
            result = true;
        } else if(obj.isEmpty()) {
            result = false;
        }

       return result;
    }

    // 내 서재에서 삭제
    public void deleteBook(String isbn, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String nickname = principalDetails.getUser().getNickname();

        libRepository.deleteById(new LibId(isbn, nickname));
    }

    // 내 서재에 저장된 수를 기준으로 원하는 범위의 순위를 인수로 받아 상위 도서데이터 가져오기
    public List<Lib> findTopBook(int x, int y) {

        List<Lib> books = libRepository.findTopBook();

        List<Lib> topBooks = new ArrayList<>();

        for(int i=x; i<y; i++) {
            topBooks.add(books.get(i));
        }

        return topBooks;
    }

    // 각 도서별 내 서재에 담긴 수 가져오기
    public Integer findBookCount(String isbn) {

        Integer count = libRepository.countBook(isbn);

        return count;
    }

    // 원하는 범위의 순위를 인수로 받아 도서데이터와 해당 도서가 서재에 담긴 수를 Map에 한 번에 담기
    public Map<Lib, Integer> combineBookMap(int x, int y) {

        // findTopBook메소드로 순위에 해당하는 도서데이터를 List로 받음
        List<Lib> topBooks = findTopBook(x,y);

        // 상위도서데이터와 서재에 담긴 수를 Map에 담아 한 번에 전달
        // 1위 도서부터 순서대로 보여주기 위해 데이터출력 순서가 보장되는 LinkedHashMap 사용
        LinkedHashMap<Lib, Integer> bookMap = new LinkedHashMap<>();

        // topBooks List 내 객체들을 반복문을 통해 bookMap의 Key값으로 담음
        // topBooks의 isbn값으로 해당 도서가 내 서재에 담긴 수를 변수에 담아 value값으로 담음
        for(int i=0; i<(y-x); i++) {
            Integer topCounts = findBookCount(topBooks.get(i).getBook().getIsbn());
            bookMap.put(topBooks.get(i),topCounts);
        }

        return bookMap;
    }

    // 내 서재에 도서를 담은 모든 회원들의 MBTI 정보를 얻기
    public List<String> allMbtiByNickname() {

        List<String>allNicknames = libRepository.findAllNickname();

        List<String> mbtis = new ArrayList<>();

        for(int i=0; i<allNicknames.size(); i++) {
            String nickname = allNicknames.get(i);
            mbtis.add(userRepository.findMbtidByNickname(nickname));
        }

        return mbtis;
    }

    // 내 서재에 도서를 담은 모든 회원들의 MBTI 정보들의 중복값을 계산해 Map에 담는다
    public Map<String, Integer> countMbtiDup() {

        // 내 서재에 도서를 담은 회원들의 MBTI 리스트
        List<String> mbtiList = allMbtiByNickname();

        Map<String, Integer> countMbtiDupMap = new HashMap<>();

        // MBTI 리스트를 반복문 돌리며 key(mbti명)와 value(중복횟수)에 담는다
        for(String str : mbtiList) {
            Integer count = countMbtiDupMap.get(str);
            if(count==null) {
                countMbtiDupMap.put(str,1);
            } else {
                countMbtiDupMap.put(str, count+1);
            }
        }

        return countMbtiDupMap;
    }

    // 내 서재에 도서를 담은 회원들의 MBTI 정보들의 중복값들을 담은 Map에서 value 기준으로 내림차순 정렬
    public Map<String, Integer> descendingTopMbti() {

        // Collections.sort()를 사용하기 위해 entrySet()을 이용, Map을 List 형태로 저장
        Map<String, Integer> map = new HashMap<>();
        map = countMbtiDup();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Entry 내장함수 comparingByValue()를 사용하여 내림차순 정렬
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 정렬 후 LinkedHashMap에 다시 저장
        Map<String, Integer> topMbtiDescending = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry : entryList) {
            topMbtiDescending.put(entry.getKey(), entry.getValue());
        }

        return topMbtiDescending;
    }

    // 원하는 순위를 인수로 받아 상위 MBTI 출력
    public Map<String, Integer> findTopMbti(int x) {

        // 상위 9건의 MBTI 데이터 담기
        Map<String, Integer> Mbti = descendingTopMbti();
        Map<String, Integer> topMbti = new LinkedHashMap<>();

        // 인수로 받은 순위만큼 반복문을 돌려 LinkedHashMap에 저장
        Integer i = 0;

        for (Map.Entry<String, Integer> entry : Mbti.entrySet()) {
            topMbti.put(entry.getKey(), entry.getValue());
            i++;
            if(i==x) {
                break;
            }
        }

        return topMbti;
    }
}