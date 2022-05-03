package lee.hawoob.finalproject.repository;

import lee.hawoob.finalproject.entity.Lib;
import lee.hawoob.finalproject.entity.LibId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibRepository extends JpaRepository<Lib, LibId> {
  
}