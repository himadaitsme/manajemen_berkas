package kpp.payakumbuh.manajemenberkas.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kpp.payakumbuh.manajemenberkas.api.request.DetilPermintaanReq;
import kpp.payakumbuh.manajemenberkas.entity.DetilPermintaan;
import kpp.payakumbuh.manajemenberkas.response.ResponseTemplate;
import kpp.payakumbuh.manajemenberkas.service.DetilPermintaanService;

@RestController
@RequestMapping("/api/detil-permintaan")
public class DetilPermintaanController {

	@Autowired
	DetilPermintaanService detilPermintaanService;
	
	@GetMapping(value = "/list")
	public ResponseTemplate getListDetilPermintaan(@RequestParam(name = "idPermintaan") String idPermintaan) {
		
		List<DetilPermintaan> listDetilPermintaan = detilPermintaanService.findListDetilPermintaanByIdPermintaan(idPermintaan);
		
		ResponseTemplate response = new ResponseTemplate();
		response.setStatus("success");
		response.setCode("00");
		response.setMessage("berhasil get list detil");
		response.setData(listDetilPermintaan);
		return response;
	}
	
	@PostMapping(value = "/simpan")
	public ResponseTemplate simpanDetilPermintaan(@RequestBody DetilPermintaanReq request) {
		
		DetilPermintaan	detilPermintaan = new DetilPermintaan();
		detilPermintaan.setId(request.getId());
		detilPermintaan.setIdPermintaan(request.getIdPermintaan());
		detilPermintaan.setNpwp15(request.getNpwp15());
		detilPermintaan.setTahunPajak(request.getTahunPajak());
		
		try {
			detilPermintaanService.simpanDetilPermintaan(detilPermintaan);
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil simpan detil permintaan");
			
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
