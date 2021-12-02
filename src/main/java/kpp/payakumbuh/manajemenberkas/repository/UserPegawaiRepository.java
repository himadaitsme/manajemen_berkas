package kpp.payakumbuh.manajemenberkas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.entity.UserPegawai;

@Repository
public interface UserPegawaiRepository extends JpaRepository<UserPegawai, String> {

	List<UserPegawai> findByIdUnitOrgAndIdJabatan(String idUnitOrg, String idJabatan);
}
