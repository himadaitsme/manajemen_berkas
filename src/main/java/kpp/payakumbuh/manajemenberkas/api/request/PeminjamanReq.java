package kpp.payakumbuh.manajemenberkas.api.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeminjamanReq {

	private String id;
	private String npwp15;
	private String namaWp;
	private Integer tahunPajak;
	private String nomorNd;
	private LocalDate tanggalNd;
}
