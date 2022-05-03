package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.LibId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibRepository extends JpaRepository<Lib, LibId> {

    Long countByBookIsbn(String isbn);
}