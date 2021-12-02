package kpp.payakumbuh.manajemenberkas.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DATA_PEMINJAMAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peminjaman {

	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "NPWP15")
	private String npwp15;
	@Column(name = "NAMA_WP")
	private String namaWp;
	@Column(name = "nomor_nd")
	private String nomorNd;
	@Column(name = "tanggal_nd")
	private LocalDate tanggalNd;
	@Column(name = "tahun_pajak")
	private Integer tahunPajak;
	@Column(name = "id_unit")
	private String idUnit;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
}
