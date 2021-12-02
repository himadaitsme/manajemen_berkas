package kpp.payakumbuh.manajemenberkas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.entity.RefJenisBerkas;
import kpp.payakumbuh.manajemenberkas.entity.UserPegawai;
import kpp.payakumbuh.manajemenberkas.repository.RefJenisBerkasRepository;
import kpp.payakumbuh.manajemenberkas.repository.UserPegawaiRepository;

@Service
public class RefJenisBerkasService {

	@Autowired
	RefJenisBerkasRepository refJenisBerkasRepository;
	
	public List<RefJenisBerkas> getListJenisBerkasSemua() {
		
		return refJenisBerkasRepository.findAll();
	}

	public List<RefJenisBerkas> getListJenisBerkasMasihBerlaku() {
		
		return refJenisBerkasRepository.findByMasihBerlaku("Y");
	}
}
