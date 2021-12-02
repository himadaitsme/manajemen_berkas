package kpp.payakumbuh.manajemenberkas.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SURAT_PEMINJAMAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuratPeminjaman {

	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "nomor_nd")
	private String nomorNd;
	@Column(name = "tanggal_nd")
	private LocalDate tanggalNd;
	@Column(name = "id_unit")
	private String idUnit;
	@Column(name = "status")
	private String status;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "approved_by")
	private String approvedBy;
	@Column(name = "approved_at")
	private LocalDateTime approvedAt;
}
