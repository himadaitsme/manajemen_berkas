package kpp.payakumbuh.manajemenberkas.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kpp.payakumbuh.manajemenberkas.api.request.PermintaanPeminjamanReq;
import kpp.payakumbuh.manajemenberkas.api.request.ProsesPeminjamanReq;
import kpp.payakumbuh.manajemenberkas.entity.SuratPeminjaman;
import kpp.payakumbuh.manajemenberkas.response.ResponseTemplate;
import kpp.payakumbuh.manajemenberkas.service.SuratPeminjamanService;

@RestController
@RequestMapping("/api/surat-peminjaman")
public class SuratPeminjamanController {

	@Autowired
	SuratPeminjamanService suratPeminjamanService;
	
	@GetMapping(value = "/list")
	public ResponseTemplate getListPermintaanPeminjaman() {
		
		List<SuratPeminjaman> listPermintaanPeminjaman = suratPeminjamanService.findListPeminjamanByIdUnit();
		
		ResponseTemplate response = new ResponseTemplate();
		response.setStatus("success");
		response.setCode("00");
		response.setMessage("berhasil get list permintaan peminjaman");
		response.setData(listPermintaanPeminjaman);
		return response;
	}
	
	@PostMapping(value = "/simpan")
	public ResponseTemplate simpanPermintaanPeminjaman(@RequestBody PermintaanPeminjamanReq request) {
		
		SuratPeminjaman permintaanPeminjaman = new SuratPeminjaman();
		permintaanPeminjaman.setId(request.getId());
		permintaanPeminjaman.setNomorNd(request.getNomorNd());
		permintaanPeminjaman.setTanggalNd(request.getTanggalNd());
		try {
			suratPeminjamanService.simpanPermintaanPeminjaman(permintaanPeminjaman);
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil simpan permintaan peminjaman");
			
			return response;
		} catch (Exception e) {
			
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("error");
			response.setCode("99");
			response.setMessage(e.getMessage());
			
			return response;
		}
	}
	
	@PostMapping(value = "/proses")
	public ResponseTemplate prosesPermintaanPeminjaman(@RequestBody ProsesPeminjamanReq request) {
		
		try {
			suratPeminjamanService.prosesPermintaanPeminjaman(request.getId(), request.getNomorNd(), request.getApproved());
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil simpan permintaan peminjaman");
			
			return response;
		} catch (Exception e) {
			
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("error");
			response.setCode("99");
			response.setMessage(e.getMessage());
			
			return response;
		}
	}
}
