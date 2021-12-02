package kpp.payakumbuh.manajemenberkas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.entity.DetilPermintaan;
import kpp.payakumbuh.manajemenberkas.repository.DetilPermintaanRepository;
import kpp.payakumbuh.manajemenberkas.security.UserLoginService;

@Service
public class DetilPermintaanService {

	
	@Autowired
	DetilPermintaanRepository detilPermintaanRepository;

	
	@Autowired
	UserLoginService userLoginService;
	
	public List<DetilPermintaan> findListDetilPermintaanByIdPermintaan(String idPermintaan){
		
		return detilPermintaanRepository.findByIdPermintaan(idPermintaan);
	}
	
	public DetilPermintaan simpanDetilPermintaan(DetilPermintaan detilPermintaan) throws Exception{

		detilPermintaan.setCreatedAt(LocalDateTime.now());
		detilPermintaan.setCreatedBy(userLoginService.getUserLogin().getIp());
		detilPermintaan.setId(UUID.randomUUID().toString());
		detilPermintaanRepository.save(detilPermintaan);
		return detilPermintaan;
	}
}