package kpp.payakumbuh.manajemenberkas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.entity.RefRak;
import kpp.payakumbuh.manajemenberkas.repository.RefRakRepository;

@Service
public class RefRakService {

	@Autowired
	RefRakRepository refRakRepository;
	
	public List<RefRak> getListRakSemua() {
		
		return refRakRepository.findAll();
	}
}
