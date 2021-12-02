package kpp.payakumbuh.manajemenberkas.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

	public LinkedHashMap<Integer, String> getListMasa() {
		
		LinkedHashMap<Integer, String> listMasa = new LinkedHashMap<Integer, String>();
		listMasa.put(1, "Januari");
		listMasa.put(2, "Februari");
		listMasa.put(3, "Maret");
		listMasa.put(4, "April");
		listMasa.put(5, "Mei");
		listMasa.put(6, "Juni");
		listMasa.put(7, "Juli");
		listMasa.put(8, "Agustus");
		listMasa.put(9, "September");
		listMasa.put(10, "Oktober");
		listMasa.put(11, "November");
		listMasa.put(12, "Desember");
		return listMasa;
	}
}
