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

import kpp.payakumbuh.manajemenberkas.api.request.PeminjamanReq;
import kpp.payakumbuh.manajemenberkas.entity.Berkas;
import kpp.payakumbuh.manajemenberkas.entity.Peminjaman;
import kpp.payakumbuh.manajemenberkas.response.ResponseTemplate;
import kpp.payakumbuh.manajemenberkas.service.PeminjamanService;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {

	@Autowired
	PeminjamanService peminjamanService;
	
	@GetMapping(value = "/list")
	public ResponseTemplate getListPeminjaman(@RequestParam(name = "npwp", required = false) String npwp, @RequestParam(name = "nomorNd", required = false) String nomorNd,  @RequestParam(name = "tahunPajak", required = false) Integer tahunPajak) {
		
		List<Peminjaman> listPeminjaman = peminjamanService.findListPeminjamanByIdUnit();
		
		List<Peminjaman> filteredListPeminjaman = new ArrayList<Peminjaman>(); 
		for (Peminjaman peminjaman : listPeminjaman) {
			
			boolean isUsed = true;
			
			if (npwp != null && !npwp.isBlank() && !npwp.equalsIgnoreCase(peminjaman.getNpwp15())) {
				
				isUsed = false;
			}
			
			if (nomorNd != null && !nomorNd.isBlank() && !nomorNd.equalsIgnoreCase(peminjaman.getNomorNd())) {
				
				isUsed = false;
			}
			
			if (tahunPajak != null && tahunPajak.intValue() != peminjaman.getTahunPajak()) {
				
				isUsed = false;
			}
			
			if (isUsed) {
				
				filteredListPeminjaman.add(peminjaman);
			}
		}
		
		ResponseTemplate response = new ResponseTemplate();
		response.setStatus("success");
		response.setCode("00");
		response.setMessage("berhasil get list peminjaman");
		response.setData(filteredListPeminjaman);
		return response;
	}
	
	@PostMapping(value = "/simpan")
	public ResponseTemplate simpanPeminjaman(@RequestBody PeminjamanReq request) {
		
		Peminjaman	peminjaman = new Peminjaman();
		peminjaman.setId(request.getId());
		peminjaman.setNpwp15(request.getNpwp15());
		peminjaman.setNomorNd(request.getNomorNd());
		peminjaman.setTanggalNd(request.getTanggalNd());
		peminjaman.setTahunPajak(request.getTahunPajak());
		peminjaman.setNamaWp(request.getNamaWp());
		try {
			peminjamanService.simpanPeminjaman(peminjaman);
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil simpan peminjaman");
			
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
