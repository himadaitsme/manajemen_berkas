package kpp.payakumbuh.manajemenberkas.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

	private String ip;
	private String nip;
	private String nama;
	private String idUnit;
}
