package kpp.payakumbuh.manajemenberkas.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.api.request.PeminjamanReq;
import kpp.payakumbuh.manajemenberkas.entity.Berkas;
import kpp.payakumbuh.manajemenberkas.entity.Peminjaman;
import kpp.payakumbuh.manajemenberkas.repository.PeminjamanRepository;
import kpp.payakumbuh.manajemenberkas.security.UserLoginService;
import kpp.payakumbuh.manajemenberkas.security.UserModel;

@Service
public class PeminjamanService {

	
	@Autowired
	PeminjamanRepository peminjamanRepository;
	
	@Autowired
	BerkasService berkasService;
	
	@Autowired
	UserLoginService userLoginService;
	
	public List<Peminjaman> findListPeminjaman(){
		
		return peminjamanRepository.findAll();
	}
	
	public List<Peminjaman> findListPeminjamanByIdUnit(){
		
		return peminjamanRepository.findByIdUnit(userLoginService.getUserLogin().getIdUnit());
	}
	
	
	public Peminjaman simpanPeminjaman(Peminjaman peminjaman) throws Exception{

		peminjaman.setCreatedAt(LocalDateTime.now());
		peminjaman.setCreatedBy(userLoginService.getUserLogin().getIp());
		peminjaman.setId(UUID.randomUUID().toString());
		peminjaman.setIdUnit(userLoginService.getUserLogin().getIdUnit());
		peminjamanRepository.save(peminjaman);
		return peminjaman;
	}
}
