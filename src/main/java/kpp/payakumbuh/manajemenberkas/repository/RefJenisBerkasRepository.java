package kpp.payakumbuh.manajemenberkas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.entity.RefJenisBerkas;

@Repository
public interface RefJenisBerkasRepository extends JpaRepository<RefJenisBerkas, Integer> {

	List<RefJenisBerkas> findByMasihBerlaku(String masihBerlaku);
}
