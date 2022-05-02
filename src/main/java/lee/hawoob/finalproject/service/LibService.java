package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.repository.LibRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LibService {

    @Autowired
    private LibRepository libRepository;

    public List<Lib> findAllByNickname(String nickname) {
        List<Lib> isbns = libRepository.findAllByNickname_Nickname(nickname);

        return isbns;
    }
}
