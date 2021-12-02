package kpp.payakumbuh.manajemenberkas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kpp.payakumbuh.manajemenberkas.entity.RefJenisBerkas;
import kpp.payakumbuh.manajemenberkas.entity.RefUnitOrganisasi;

@Repository
public interface RefUnitOrganisasiRepository extends JpaRepository<RefUnitOrganisasi, String> {

	List<RefUnitOrganisasi> findByLevelUnit(String levelUnit);
}
