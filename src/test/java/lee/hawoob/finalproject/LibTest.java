package lee.hawoob.finalproject;

import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.LibId;
import lee.hawoob.finalproject.repository.LibRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lee.hawoob.finalproject.service.LibService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.*;

@SpringBootTest
@Transactional
public class LibTest {

    @Autowired
    private LibRepository libRepository;

    @Autowired
    private LibService libService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void Libtest() {

        String usernickname = "하웁";
        String userisbn = "1130637611 979113063762732321";

         Optional<Lib> obj = libRepository.findById(new LibId(userisbn, usernickname));

        if(obj.isPresent()) {
            System.out.println("이미 보관중인 도서입니다.");
        } else if(obj.isEmpty()) {
            System.out.println("보관완료!");
        }
    }

    @Test
    void top3test() {

        List<Lib> books = libRepository.findTopBook();

        List<Lib> top3Books = new ArrayList<>();

        for(int i=0; i<3; i++) {
            System.out.println(books.get(i));
            top3Books.add(books.get(i));
        }

        System.out.println(top3Books.get(0).getBook().getTitle());
    }

    @Test
    void countBook() {

        Integer count = libRepository.countBook("1130605213 9791130605210");

        System.out.println(count);
    }

    @Test
    void combineMap() {

        List<Lib> topBooks = libService.findTopBook(3,10);

        // 상위도서데이터와 서재에 담긴 수를 Map에 담아 한 번에 전달
        // 1위 도서부터 순서대로 보여주기 위해 데이터출력 순서가 보장되는 LinkedHashMap 사용
        LinkedHashMap<Lib, Integer> bookMap = new LinkedHashMap<>();

        for(int i=0; i<7; i++) {
            Integer topCounts = libService.findBookCount(topBooks.get(i).getBook().getIsbn());
            bookMap.put(topBooks.get(i), topCounts);
        }

        System.out.println(bookMap);
    }

    @Test
    void findMbtiByNickname() {

        List<String>allNicknames = libRepository.findAllNickname();

        List<String> mbtis = new ArrayList<>();

        for(int i=0; i<allNicknames.size(); i++) {
            String nickname = allNicknames.get(i);
            mbtis.add(userRepository.findMbtidByNickname(nickname));
        }

        System.out.println(mbtis);
    }

    @Test
    public void countMbtiDup() {

        List<String> mbtiList = libService.allMbtiByNickname();

        Map<String, Integer> countMbtiDupMap = new HashMap<>();

        for(String str : mbtiList) {
            Integer count = countMbtiDupMap.get(str);
            if(count==null) {
                countMbtiDupMap.put(str,1);
            } else {
                countMbtiDupMap.put(str, count+1);
            }
        }

        for(String key: countMbtiDupMap.keySet()) {
            System.out.println(key + ":" + countMbtiDupMap.get(key));
        }
    }

    @Test
    public void descendingMbti() {
        // Collections.sort()를 사용하기 위해 entrySet()을 이용, Map을 List 형태로 저장
        Map<String, Integer> map = new HashMap<>();
        map = libService.countMbtiDup();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Entry 내장함수 comparingByValue()를 사용하여 내림차순 정렬
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 정렬 후 LinkedHashMap에 다시 저장
        Map<String, Integer> topMbtiDescending = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry : entryList) {
            topMbtiDescending.put(entry.getKey(), entry.getValue());
        }

        System.out.println(topMbtiDescending);
    }

    @Test
    public void top9Mbti() {
        // 상위 9건의 MBTI 데이터 담기
        Map<String, Integer> topMbti = libService.descendingTopMbti();
        Map<String, Integer> top9Mbti = new LinkedHashMap<>();

        Integer i = 0;

        for (Map.Entry<String, Integer> entry : topMbti.entrySet()) {
            top9Mbti.put(entry.getKey(), entry.getValue());
            i++;
            if(i==9) {
                break;
            }
        }

        System.out.println(top9Mbti);
    }
}
