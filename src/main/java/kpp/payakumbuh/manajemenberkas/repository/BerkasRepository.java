package kpp.payakumbuh.manajemenberkas.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.dto.BerkasPinjam;
import kpp.payakumbuh.manajemenberkas.entity.Berkas;

@Repository
public interface BerkasRepository extends JpaRepository<Berkas, String> {
	
	
	List<Berkas> findByNpwp15AndJenisBerkasAndMasaPajakAndTahunPajak(String npwp15, String jenisBerkas, Integer masaPajak, Integer tahunPajak);
	List<Berkas> findByNpwp15AndAndTahunPajak(String npwp15, Integer tahunPajak);
	
	@Query(value = "select * from manajemen_berkas.data_berkas db where tanggal_berkas < current_date - interval '5 YEARS'", nativeQuery = true)
	List<Berkas> findBerkasDaluarsa();

	@Query(value = "select "+ BerkasPinjam.selectQuery +" from manajemen_berkas.data_berkas db left join (select * from register_peminjaman where id_unit = :id_unit) rp on db.id  = rp.id_berkas", nativeQuery = true)
	List<BerkasPinjam> findBerkasPinjamByIdUnit(@Param(value = "id_unit") String idUnit);
	
	
}
