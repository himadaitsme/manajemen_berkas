package kpp.payakumbuh.manajemenberkas.api.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermintaanPeminjamanReq {

	private String id;
	private String nomorNd;
	private LocalDate tanggalNd;
}
