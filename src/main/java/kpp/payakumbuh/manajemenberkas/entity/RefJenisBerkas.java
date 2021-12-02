package kpp.payakumbuh.manajemenberkas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REF_JENIS_BERKAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefJenisBerkas {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "JENIS_BERKAS")
	private String jenisBerkas;
	
	@Column(name = "MASIH_BERLAKU")
	private String masihBerlaku;
	
	@Column(name = "KODE_JENIS_BERKAS")
	private String kodeJenisBerkas;
	
	@Column(name = "MASA_TAHUNAN")
	private String masaTahunan;
}
