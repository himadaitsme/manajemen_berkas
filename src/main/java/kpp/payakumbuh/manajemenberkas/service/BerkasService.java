package kpp.payakumbuh.manajemenberkas.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kpp.payakumbuh.manajemenberkas.entity.Berkas;
import kpp.payakumbuh.manajemenberkas.exception.CustomFileNotFoundException;
import kpp.payakumbuh.manajemenberkas.repository.BerkasRepository;
import kpp.payakumbuh.manajemenberkas.security.UserLoginService;
import kpp.payakumbuh.manajemenberkas.security.UserModel;

@Service
public class BerkasService {

	
	@Autowired
	BerkasRepository berkasRepository;
	
	@Autowired
	UserLoginService userLoginService;
	
	@Value("${filestorage.path}")
	String filestoragePath;
	
	public List<Berkas> findListBerkasDaluarsa(){
		
		return berkasRepository.findBerkasDaluarsa();
	}
	
	public List<Berkas> findListBerkas(){
		
		return berkasRepository.findAll();
	}
	
	public List<Berkas> findListBerkasByNpwp15AndTahunPajak(String npwp15, Integer tahunPajak){
		
		return berkasRepository.findByNpwp15AndAndTahunPajak(npwp15, tahunPajak);
	}
	
	public Berkas simpanBerkas(Berkas berkas) throws Exception{
		
		UserModel userLogin = userLoginService.getUserLogin();
		
		
		if (berkas.getId() == null || berkas.getId().isEmpty()) {
			
			List<Berkas> existingBerkas = berkasRepository.findByNpwp15AndJenisBerkasAndMasaPajakAndTahunPajak(berkas.getNpwp15(), berkas.getJenisBerkas(), berkas.getMasaPajak(), berkas.getTahunPajak());
			
			if (existingBerkas.isEmpty()) {
				
				berkas.setId(UUID.randomUUID().toString());
				berkas.setCreatedBy(userLogin.getIp());
				berkas.setCreatedAt(LocalDateTime.now());
				return berkasRepository.save(berkas);
			} else {
				
				throw new Exception("Berkas Yang Sama Sudah Terdaftar");
			}
		} else {
			
			Optional<Berkas> existingSuratKeteranganBebas = berkasRepository.findById(berkas.getId());
			
			
			if (existingSuratKeteranganBebas.isPresent()) {
				
				Berkas oldSuratKeteranganBebas = existingSuratKeteranganBebas.get();
				
				berkas.setId(oldSuratKeteranganBebas.getId());
				berkas.setUpdatedBy(userLogin.getIp());
				berkas.setUpdatedAt(LocalDateTime.now());
				return berkasRepository.save(berkas);
				
			} else {
				
				throw new Exception("Berkas tidak ditemukan");
			}
		}
	}
	
	public Berkas uploadBerkas(String id, MultipartFile file) throws Exception{
		
		Optional<Berkas> optBerkas = berkasRepository.findById(id);
		
		if (optBerkas.isPresent()) {
			Berkas berkas = optBerkas.get();
			if (berkas.getFilename() != null && !berkas.getFilename().isBlank()) {
				
				throw new Exception("Berkas sudah pernah diupload");
			}
			String filename = String.format("%s-%s-%04d-%02d.pdf", berkas.getNpwp15(), berkas.getJenisBerkas(), berkas.getTahunPajak(), berkas.getMasaPajak());
			Path path = Paths.get(filestoragePath, berkas.getNpwp15(), filename);
			
			try (InputStream inputStream = file.getInputStream()) {
				
				if (!Files.exists(path.getParent())) {
					
					Files.createDirectory(path.getParent());
				}
				
				Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
			}
			
			berkas.setFilename(filename);
			berkas.setFilesize(new BigDecimal(file.getSize()));
			berkasRepository.save(berkas);
			
			return berkas;
		} else {
			
			throw new Exception("Berkas tidak ditemukan");
		}
	}
	
	public Resource downloadBerkas(String id) {
		
		Optional<Berkas> optBerkas = berkasRepository.findById(id);
		
		if (optBerkas.isPresent()) {
			Berkas berkas = optBerkas.get();
			
			try {
				Path path = Paths.get(filestoragePath, berkas.getNpwp15(), berkas.getFilename());
				Resource resource = new UrlResource(path.toUri());
				if(resource.exists() || resource.isReadable()) {
					return resource;
				}
				else {
					
					throw new CustomFileNotFoundException("Could not read file: " + berkas.getFilename());

				}
			} catch (MalformedURLException e) {
				
				throw new CustomFileNotFoundException("Could not read file: " + berkas.getFilename(), e);
			}
		} else {
			
			throw new CustomFileNotFoundException("Berkas tidak ditemukan");
		}
	}
}
