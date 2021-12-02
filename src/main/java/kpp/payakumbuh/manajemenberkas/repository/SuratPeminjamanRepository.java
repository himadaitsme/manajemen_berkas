package kpp.payakumbuh.manajemenberkas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.entity.SuratPeminjaman;

@Repository
public interface SuratPeminjamanRepository extends JpaRepository<SuratPeminjaman, String> {
	
	List<SuratPeminjaman> findByIdUnit(String idUnit);
	List<SuratPeminjaman> findByIdAndNomorNd(String id, String nomorNd);
}
