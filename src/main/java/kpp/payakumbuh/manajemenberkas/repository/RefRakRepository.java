package kpp.payakumbuh.manajemenberkas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.entity.RefRak;

@Repository
public interface RefRakRepository extends JpaRepository<RefRak, String> {
	
}
