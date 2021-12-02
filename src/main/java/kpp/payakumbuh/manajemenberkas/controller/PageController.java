package kpp.payakumbuh.manajemenberkas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kpp.payakumbuh.manajemenberkas.service.CommonService;
import kpp.payakumbuh.manajemenberkas.service.RefJenisBerkasService;
import kpp.payakumbuh.manajemenberkas.service.RefRakService;
import kpp.payakumbuh.manajemenberkas.service.RefUnitOrganisasiService;
import kpp.payakumbuh.manajemenberkas.service.UserPegawaiService;

@Controller
public class PageController {
	
	@Autowired
	UserPegawaiService userPegawaiService;
	
	@Autowired
	RefJenisBerkasService refJenisBerkasService;
	
	@Autowired
	RefUnitOrganisasiService refUnitOrganisasiService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	RefRakService refRakService;
	
	@GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        return "home";
    }
	
	@GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) boolean error, Model model) {
		model.addAttribute("error", error);
		
		if (error) {
			
			model.addAttribute("message", "terjadi kesalahan");
		}
		
		return "login";
    }
	
	@GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }
	
	@GetMapping("/berkas")
    public String berkas(Model model) {
		model.addAttribute("listSemuaJenisBerkas", refJenisBerkasService.getListJenisBerkasSemua());
		model.addAttribute("listMasaPajak", commonService.getListMasa());
		model.addAttribute("listSemuaRak", refRakService.getListRakSemua());
		return "berkas";
    }
	
	@GetMapping("/jenis-berkas")
    public String jenisBerkas(Model model) {

        return "jenis-berkas";
    }
	
	@GetMapping("/peminjaman")
    public String pencarian(Model model) {
		model.addAttribute("listSemuaJenisBerkas", refJenisBerkasService.getListJenisBerkasSemua());
		model.addAttribute("listMasaPajak", commonService.getListMasa());
		return "peminjaman";
    }
	
	@GetMapping("/persetujuan")
    public String persetujuan(Model model) {
		model.addAttribute("listSemuaJenisBerkas", refJenisBerkasService.getListJenisBerkasSemua());
		model.addAttribute("listMasaPajak", commonService.getListMasa());
		model.addAttribute("listUnitOrganisasi", refUnitOrganisasiService.getListSemuaSeksi());		
		return "persetujuan";
    }
	
	@GetMapping("/retensi")
    public String retensi(Model model) {
		model.addAttribute("listSemuaJenisBerkas", refJenisBerkasService.getListJenisBerkasSemua());
		model.addAttribute("listMasaPajak", commonService.getListMasa());
		return "retensi";
    }
}
