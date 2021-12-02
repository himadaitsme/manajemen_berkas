package kpp.payakumbuh.manajemenberkas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.entity.UserPegawai;
import kpp.payakumbuh.manajemenberkas.repository.UserPegawaiRepository;

@Service
public class UserPegawaiService {

	@Autowired
	UserPegawaiRepository userPegawaiRepository;
	
	public List<UserPegawai> getPelaksanaSeksiPelayanan() {
		
		return userPegawaiRepository.findByIdUnitOrgAndIdJabatan("1", "2");
	}
}
