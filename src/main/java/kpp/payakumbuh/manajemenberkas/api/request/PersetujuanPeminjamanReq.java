package kpp.payakumbuh.manajemenberkas.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersetujuanPeminjamanReq {

	private String idRegister;
	private String idBerkas;
	private Boolean persetujuan;
}
