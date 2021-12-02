package kpp.payakumbuh.manajemenberkas.service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kpp.payakumbuh.manajemenberkas.response.ResponseTemplate;

@RestController
public class MasterFileService {

	@Value("${api.master.baseurl}") 
	private String API_MASTER_BASEURL;
	
	@GetMapping(value="/master-file")
	public ResponseTemplate getDataMasterFile(@RequestParam(name = "npwp15") String npwp15) {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = API_MASTER_BASEURL + "/master-file?npwp15="+npwp15;
		ResponseEntity<ResponseTemplate> response = restTemplate.getForEntity(fooResourceUrl, ResponseTemplate.class);
		return response.getBody();
	}
}
