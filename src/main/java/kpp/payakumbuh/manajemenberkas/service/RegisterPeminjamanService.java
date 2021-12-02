package kpp.payakumbuh.manajemenberkas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.dto.BerkasPinjam;
import kpp.payakumbuh.manajemenberkas.entity.RegisterPeminjaman;
import kpp.payakumbuh.manajemenberkas.repository.BerkasRepository;
import kpp.payakumbuh.manajemenberkas.repository.RegisterPeminjamanRepository;
import kpp.payakumbuh.manajemenberkas.security.UserLoginService;

@Service
public class RegisterPeminjamanService {

	
	@Autowired
	RegisterPeminjamanRepository registerPeminjamanRepository;
	
	@Autowired
	BerkasRepository berkasRepository;
	
	@Autowired
	UserLoginService userLoginService;
	
	public List<BerkasPinjam> findListBerkasPinjam(){
		
		return berkasRepository.findBerkasPinjamByIdUnit(userLoginService.getUserLogin().getIdUnit());
	}
	
	public List<BerkasPinjam> findListRegisterPeminjaman(){
		
		return registerPeminjamanRepository.findRegisterByIdUnit(userLoginService.getUserLogin().getIdUnit());
	}
	
	public RegisterPeminjaman simpanRegisterPeminjaman(RegisterPeminjaman registerPeminjaman) throws Exception{

		List<RegisterPeminjaman> listRegister = registerPeminjamanRepository.findByIdBerkasAndIdUnit(registerPeminjaman.getIdBerkas(), userLoginService.getUserLogin().getIdUnit());
		
		if (listRegister.isEmpty()) {
			
			registerPeminjaman.setCreatedAt(LocalDateTime.now());
			registerPeminjaman.setCreatedBy(userLoginService.getUserLogin().getIp());
			registerPeminjaman.setId(UUID.randomUUID().toString());
			registerPeminjaman.setIdUnit(userLoginService.getUserLogin().getIdUnit());
			registerPeminjaman.setStatusApproval("0");
			registerPeminjamanRepository.save(registerPeminjaman);
			return registerPeminjaman;
		} else {
			
			RegisterPeminjaman oldRegister = listRegister.get(0);

			switch (oldRegister.getStatusApproval()) {
			case "0":
				throw new Exception("Permintaan Sudah Pernah Dilakukan");
			case "1":
				throw new Exception("Permintaan Sudah Pernah Disetujui");
			case "2":
				oldRegister.setCreatedAt(LocalDateTime.now());
				oldRegister.setCreatedBy(userLoginService.getUserLogin().getIp());
				oldRegister.setApprovedAt(null);
				oldRegister.setApprovedBy(null);
				oldRegister.setStatusApproval("0");
				registerPeminjamanRepository.save(oldRegister);
				return oldRegister;
			default:
				throw new Exception("Status Tidak Valid");
			}
		}	
	}
	
	public RegisterPeminjaman persetujuanPeminjaman(String idRegister, Boolean persetujuan) throws Exception{
		
		Optional<RegisterPeminjaman> optRegister = registerPeminjamanRepository.findById(idRegister);
		
		if (optRegister.isPresent()) {
			
			RegisterPeminjaman registerPeminjaman = optRegister.get();
			
			if ("0".equals(registerPeminjaman.getStatusApproval())) {
				
				if (persetujuan) {
				
					registerPeminjaman.setStatusApproval("1");	
				} else {
					
					registerPeminjaman.setStatusApproval("2");
				}
				
				registerPeminjaman.setApprovedAt(LocalDateTime.now());
				registerPeminjaman.setApprovedBy(userLoginService.getUserLogin().getIp());
				registerPeminjamanRepository.save(registerPeminjaman);
				
				return registerPeminjaman;
				
			} else {
				
				throw new Exception("Data Peminjaman Sudah Pernah Diproses");
			}
			
		} else {
			
			throw new Exception("Data Peminjaman Tidak Ditemukan");
		}
	}
}
