package kpp.payakumbuh.manajemenberkas.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kpp.payakumbuh.manajemenberkas.api.request.PersetujuanPeminjamanReq;
import kpp.payakumbuh.manajemenberkas.api.request.RegisterPeminjamanReq;
import kpp.payakumbuh.manajemenberkas.dto.BerkasPinjam;
import kpp.payakumbuh.manajemenberkas.entity.RegisterPeminjaman;
import kpp.payakumbuh.manajemenberkas.response.ResponseTemplate;
import kpp.payakumbuh.manajemenberkas.service.RegisterPeminjamanService;

@RestController
@RequestMapping("/api/register-peminjaman")
public class RegisterPeminjamanController {

	@Autowired
	RegisterPeminjamanService registerPeminjamanService;
	
	@GetMapping(value = "/list")
	public ResponseTemplate getListBerkas(@RequestParam(name = "npwp", required = false) String npwp, @RequestParam(name = "jenisBerkas", required = false) String jenisBerkas, @RequestParam(name = "masaPajak", required = false) Integer masaPajak, @RequestParam(name = "tahunPajak", required = false) Integer tahunPajak) {
		
		List<BerkasPinjam> listBerkas = registerPeminjamanService.findListBerkasPinjam();
		
		ResponseTemplate response = new ResponseTemplate();
		response.setStatus("success");
		response.setCode("00");
		response.setMessage("berhasil get list berkas");
		response.setData(listBerkas);
		return response;
	}
	
	@GetMapping(value = "/listRegister")
	public ResponseTemplate getListRegisterPeminjaman(@RequestParam(name = "npwp", required = false) String npwp, @RequestParam(name = "jenisBerkas", required = false) String jenisBerkas, @RequestParam(name = "masaPajak", required = false) Integer masaPajak, @RequestParam(name = "tahunPajak", required = false) Integer tahunPajak) {
		
		List<BerkasPinjam> listBerkas = registerPeminjamanService.findListRegisterPeminjaman();
		
		ResponseTemplate response = new ResponseTemplate();
		response.setStatus("success");
		response.setCode("00");
		response.setMessage("berhasil get list register");
		response.setData(listBerkas);
		return response;
	}
	
	@PostMapping(value = "/simpan")
	public ResponseTemplate simpanRegisterPeminjaman(@RequestBody RegisterPeminjamanReq request) {
		
		RegisterPeminjaman	registerPeminjaman = new RegisterPeminjaman();
		registerPeminjaman.setId(request.getId());
		registerPeminjaman.setIdBerkas(request.getIdBerkas());
	
		try {
			registerPeminjamanService.simpanRegisterPeminjaman(registerPeminjaman);
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil simpan register peminjaman");
			
			return response;
		} catch (Exception e) {
			
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("error");
			response.setCode("99");
			response.setMessage(e.getMessage());
			
			return response;
		}
	}
	
	@PostMapping(value = "/persetujuan")
	public ResponseTemplate persetujuanRegisterPeminjaman(@RequestBody PersetujuanPeminjamanReq request) {
		
	
		try {
			registerPeminjamanService.persetujuanPeminjaman(request.getIdRegister(), request.getPersetujuan());
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil simpan persetujuan peminjaman");
			
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
