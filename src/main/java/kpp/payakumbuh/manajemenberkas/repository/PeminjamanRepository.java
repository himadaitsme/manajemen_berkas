package kpp.payakumbuh.manajemenberkas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.entity.Peminjaman;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, String> {
	
	List<Peminjaman> findByIdUnit(String idUnit);
}
