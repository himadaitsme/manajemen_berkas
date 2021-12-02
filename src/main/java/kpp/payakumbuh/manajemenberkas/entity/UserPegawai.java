package kpp.payakumbuh.manajemenberkas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_PEGAWAI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPegawai {

	@Id
	@Column(name = "IP")
	private String ip;
	
	@Column(name = "NIP")
	private String nip;
	
	@Column(name = "NAMA")
	private String nama;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ID_UNIT_ORG")
	private String idUnitOrg;
	
	@Column(name = "ID_JABATAN")
	private String idJabatan;
}
