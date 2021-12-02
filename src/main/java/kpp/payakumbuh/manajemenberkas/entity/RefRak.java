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
@Table(name = "REF_RAK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefRak {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "RAK")
	private String rak;
	
	@Column(name = "JUMLAH_TINGKAT")
	private Integer jumlahTingkat;
	
	@Column(name = "JUMLAH_KARDUS")
	private Integer jumlahKardus;
}
