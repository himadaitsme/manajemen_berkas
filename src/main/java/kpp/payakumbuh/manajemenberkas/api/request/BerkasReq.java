package kpp.payakumbuh.manajemenberkas.api.request;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BerkasReq {

	private String id;
	private String npwp15;
	private String namaWp;
	private String jenisBerkas;
	private String namaJenisBerkas;
	private Integer masaPajak;
	private Integer tahunPajak;
	private LocalDate tanggalBerkas;
	private String noRak;
	private String noTingkat;
	private String noKardus;
	private String noUrutMap;
}
