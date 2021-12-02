package kpp.payakumbuh.manajemenberkas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.dto.BerkasPinjam;
import kpp.payakumbuh.manajemenberkas.entity.RegisterPeminjaman;

@Repository
public interface RegisterPeminjamanRepository extends JpaRepository<RegisterPeminjaman, String> {
	
	@Query(value = "select "+ BerkasPinjam.selectQuery +" from manajemen_berkas.data_berkas db left join (select * from register_peminjaman where id_unit = :id_unit) rp on db.id  = rp.id_berkas", nativeQuery = true)
	List<BerkasPinjam> findBerkasPinjamByIdUnit(@Param(value = "id_unit") String idUnit);
	
	List<RegisterPeminjaman> findByIdBerkasAndIdUnit(String idBerkas, String idUnit);
	
	@Query(value = "select * from register_peminjaman where id_unit = :id_unit and id_surat_permintaan is null", nativeQuery = true)
	List<RegisterPeminjaman> findRegisterUnassignByIdUnit(@Param(value = "id_unit") String idUnit);
	
	@Query(value = "select "+ BerkasPinjam.selectQuery +" from (select * from register_peminjaman where id_unit = :id_unit) rp left join manajemen_berkas.data_berkas db on db.id  = rp.id_berkas", nativeQuery = true)
	List<BerkasPinjam> findRegisterByIdUnit(@Param(value = "id_unit") String idUnit);
}
