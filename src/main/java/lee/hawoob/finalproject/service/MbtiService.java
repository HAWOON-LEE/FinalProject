package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.entity.Mbti;
import lee.hawoob.finalproject.repository.MbtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MbtiService {

    @Autowired
    private MbtiRepository mbtiRepository;

    public Iterable<Mbti> selectAll() {
        return mbtiRepository.findAll();
    }
}
