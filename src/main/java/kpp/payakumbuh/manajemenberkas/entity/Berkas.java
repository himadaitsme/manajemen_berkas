package kpp.payakumbuh.manajemenberkas.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DATA_BERKAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Berkas {

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NPWP15")
	private String npwp15;
	
	@Column(name = "NAMA_WP")
	private String namaWp;
	
	@Column(name = "JENIS_BERKAS") 
	private String jenisBerkas;
	
	@Column(name = "NAMA_JENIS_BERKAS") 
	private String namaJenisBerkas;
	
	@Column(name = "MASA_PAJAK") 
	private Integer masaPajak;
	
	@Column(name = "TAHUN_PAJAK") 
	private Integer tahunPajak;
	
	@Column(name = "TANGGAL_BERKAS") 
	private LocalDate tanggalBerkas;
	
	@Column(name = "NO_RAK") 
	private String noRak;
	
	@Column(name = "NO_Tingkat") 
	private String noTingkat;
	
	@Column(name = "NO_KARDUS") 
	private String noKardus;
	
	@Column(name = "NO_URUT_MAP") 
	private String noUrutMap;
	
	@Column(name = "CREATED_BY") 
	private String createdBy;
	
	@Column(name = "UPDATED_BY") 
	private String updatedBy;
	
	@Column(name = "CREATED_AT") 
	private LocalDateTime createdAt;
	
	@Column(name = "UPDATED_AT") 
	private LocalDateTime updatedAt;
	
	@Column(name = "FILENAME") 
	private String filename;
	
	@Column(name = "FILESIZE") 
	private BigDecimal filesize;

}
