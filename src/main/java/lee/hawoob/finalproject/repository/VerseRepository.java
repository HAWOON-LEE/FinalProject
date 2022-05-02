package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Verse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerseRepository extends JpaRepository<Verse, Long> {
}
