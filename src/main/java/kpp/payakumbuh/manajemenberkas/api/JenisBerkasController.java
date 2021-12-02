package kpp.payakumbuh.manajemenberkas.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kpp.payakumbuh.manajemenberkas.entity.RefJenisBerkas;
import kpp.payakumbuh.manajemenberkas.response.ResponseTemplate;
import kpp.payakumbuh.manajemenberkas.service.RefJenisBerkasService;

@RestController
@RequestMapping("/api/jenis-berkas")
public class JenisBerkasController {

	@Autowired
	RefJenisBerkasService refJenisBerkasService;
	
	@GetMapping(value = "/list")
	public ResponseTemplate getListJenisBerkas() {
		
		List<RefJenisBerkas> listBerkas = refJenisBerkasService.getListJenisBerkasSemua();
		
		ResponseTemplate response = new ResponseTemplate();
		response.setStatus("success");
		response.setCode("00");
		response.setMessage("berhasil get list jenis berkas");
		response.setData(listBerkas);
		return response;
	}
}
