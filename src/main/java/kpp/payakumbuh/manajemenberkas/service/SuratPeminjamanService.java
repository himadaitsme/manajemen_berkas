package kpp.payakumbuh.manajemenberkas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.payakumbuh.manajemenberkas.entity.RegisterPeminjaman;
import kpp.payakumbuh.manajemenberkas.entity.SuratPeminjaman;
import kpp.payakumbuh.manajemenberkas.repository.RegisterPeminjamanRepository;
import kpp.payakumbuh.manajemenberkas.repository.SuratPeminjamanRepository;
import kpp.payakumbuh.manajemenberkas.security.UserLoginService;

@Service
public class SuratPeminjamanService {

	
	@Autowired
	SuratPeminjamanRepository suratPermintaanPeminjamanRepository;
	
	@Autowired
	RegisterPeminjamanRepository registerPeminjamanRepository;
	
	@Autowired
	UserLoginService userLoginService;
	
	public List<SuratPeminjaman> findListPeminjaman(){
		
		return suratPermintaanPeminjamanRepository.findAll();
	}
	
	public List<SuratPeminjaman> findListPeminjamanByIdUnit(){
		
		return suratPermintaanPeminjamanRepository.findByIdUnit(userLoginService.getUserLogin().getIdUnit());
	}
	
	
	public SuratPeminjaman simpanPermintaanPeminjaman(SuratPeminjaman permintaanPeminjaman) throws Exception{

		List<RegisterPeminjaman> listRegisterUnassign = registerPeminjamanRepository.findRegisterUnassignByIdUnit(userLoginService.getUserLogin().getIdUnit());
		
		if (listRegisterUnassign.isEmpty()) {
			
			throw new Exception("Daftar Peminjaman Kosong");
		}
		
		permintaanPeminjaman.setCreatedAt(LocalDateTime.now());
		permintaanPeminjaman.setCreatedBy(userLoginService.getUserLogin().getIp());
		permintaanPeminjaman.setId(UUID.randomUUID().toString());
		permintaanPeminjaman.setIdUnit(userLoginService.getUserLogin().getIdUnit());
		permintaanPeminjaman.setStatus("0");
		suratPermintaanPeminjamanRepository.save(permintaanPeminjaman);
		
		for (RegisterPeminjaman registerPeminjaman : listRegisterUnassign) {
			
			registerPeminjaman.setIdSuratPermintaan(permintaanPeminjaman.getId());
			registerPeminjamanRepository.save(registerPeminjaman);
		}
		
		
		return permintaanPeminjaman;
	}
	
	public SuratPeminjaman prosesPermintaanPeminjaman(String id, String nomorNd, Boolean isApprove) throws Exception{

		List<SuratPeminjaman> listPermintaan = suratPermintaanPeminjamanRepository.findByIdAndNomorNd(id, nomorNd);
		
		if (!listPermintaan.isEmpty()) {
			
			SuratPeminjaman permintaanPeminjaman = listPermintaan.get(0);
			permintaanPeminjaman.setApprovedAt(LocalDateTime.now());
			permintaanPeminjaman.setApprovedBy(userLoginService.getUserLogin().getIp());
			if (isApprove) {
				permintaanPeminjaman.setStatus("1");
			} else {
				permintaanPeminjaman.setStatus("2");
			}
			
			suratPermintaanPeminjamanRepository.save(permintaanPeminjaman);
			return permintaanPeminjaman;
		} else {
			
			throw new Exception("Permintaan Tidak Ditemukan");
		}
	}
}