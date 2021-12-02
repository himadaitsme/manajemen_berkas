package kpp.payakumbuh.manajemenberkas.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kpp.payakumbuh.manajemenberkas.api.request.BerkasReq;
import kpp.payakumbuh.manajemenberkas.entity.Berkas;
import kpp.payakumbuh.manajemenberkas.exception.CustomFileNotFoundException;
import kpp.payakumbuh.manajemenberkas.response.ResponseTemplate;
import kpp.payakumbuh.manajemenberkas.service.BerkasService;

@RestController
@RequestMapping("/api/berkas")
public class BerkasController {

	@Autowired
	BerkasService berkasService;
	
	@GetMapping(value = "/list")
	public ResponseTemplate getListBerkas(@RequestParam(name = "npwp", required = false) String npwp, @RequestParam(name = "jenisBerkas", required = false) String jenisBerkas, @RequestParam(name = "masaPajak", required = false) Integer masaPajak, @RequestParam(name = "tahunPajak", required = false) Integer tahunPajak) {
		
		List<Berkas> listBerkas = berkasService.findListBerkas();
		List<Berkas> filteredListBerkas = new ArrayList<Berkas>(); 
		for (Berkas berkas : listBerkas) {
			
			boolean isUsed = true;
			
			if (npwp != null && !npwp.isBlank() && !npwp.equalsIgnoreCase(berkas.getNpwp15())) {
				
				isUsed = false;
			}
			
			if (jenisBerkas != null && !jenisBerkas.isBlank() && !jenisBerkas.equalsIgnoreCase(berkas.getJenisBerkas())) {
				
				isUsed = false;
			}
			
			if (masaPajak != null && masaPajak.intValue() != berkas.getMasaPajak()) {
				
				isUsed = false;
			}
			
			if (tahunPajak != null && tahunPajak.intValue() != berkas.getTahunPajak()) {
				
				isUsed = false;
			}
			
			if (isUsed) {
				
				filteredListBerkas.add(berkas);
			}
		}
		
		ResponseTemplate response = new ResponseTemplate();
		response.setStatus("success");
		response.setCode("00");
		response.setMessage("berhasil get list berkas");
		response.setData(filteredListBerkas);
		return response;
	}
	
	@PostMapping(value = "/simpan")
	public ResponseTemplate simpanBerkas(@RequestBody BerkasReq request) {
		
		Berkas	berkas = new Berkas();
		berkas.setId(request.getId());
		berkas.setNpwp15(request.getNpwp15());
		berkas.setNamaWp(request.getNamaWp());
		berkas.setJenisBerkas(request.getJenisBerkas());
		berkas.setNamaJenisBerkas(request.getNamaJenisBerkas());
		berkas.setMasaPajak(request.getMasaPajak());
		berkas.setTahunPajak(request.getTahunPajak());
		berkas.setTanggalBerkas(request.getTanggalBerkas());
		berkas.setNoRak(request.getNoRak());
		berkas.setNoTingkat(request.getNoTingkat());
		berkas.setNoKardus(request.getNoKardus());
		berkas.setNoUrutMap(request.getNoUrutMap());
		
		try {
			berkasService.simpanBerkas(berkas);
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil simpan berkas");
			
			return response;
		} catch (Exception e) {
			
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("error");
			response.setCode("99");
			response.setMessage(e.getMessage());
			
			return response;
		}
	}
	
	@PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseTemplate uploadBerkas(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
		
		
		try {
			berkasService.uploadBerkas(id, file);
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("success");
			response.setCode("00");
			response.setMessage("berhasil upload berkas");
			
			return response;
		} catch (Exception e) {
			
			ResponseTemplate response = new ResponseTemplate();
			response.setStatus("error");
			response.setCode("99");
			response.setMessage(e.getMessage());
			
			return response;
		}
	}
	
	@GetMapping(value = "/download")
	public ResponseEntity<Resource> downloadBerkas(@RequestParam("id") String id) {
		
			Resource file = berkasService.downloadBerkas(id);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@ExceptionHandler(CustomFileNotFoundException.class)
	public ResponseEntity<?> handleCustomFileNotFound(CustomFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
