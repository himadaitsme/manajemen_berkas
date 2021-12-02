package kpp.payakumbuh.manajemenberkas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.entity.RefUnitOrganisasi;
import kpp.payakumbuh.manajemenberkas.repository.RefUnitOrganisasiRepository;

@Service
public class RefUnitOrganisasiService {

	@Autowired
	RefUnitOrganisasiRepository refUnitOrganisasiRepository;
	
	public List<RefUnitOrganisasi> getListSemuaUnit() {
		
		return refUnitOrganisasiRepository.findAll();
	}

	public List<RefUnitOrganisasi> getListSemuaSeksi() {
		
		return refUnitOrganisasiRepository.findByLevelUnit("1");
	}
}
