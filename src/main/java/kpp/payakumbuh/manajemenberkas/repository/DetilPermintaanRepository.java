package kpp.payakumbuh.manajemenberkas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.entity.DetilPermintaan;

@Repository
public interface DetilPermintaanRepository extends JpaRepository<DetilPermintaan, String> {
	
	List<DetilPermintaan> findByIdPermintaan(String idPermintaan);
}
