package kpp.payakumbuh.manajemenberkas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REF_UNIT_ORG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefUnitOrganisasi {

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "LEVEL_UNIT")
	private String levelUnit;
	
	@Column(name = "NAMA_UNIT")
	private String namaUnit;
}
