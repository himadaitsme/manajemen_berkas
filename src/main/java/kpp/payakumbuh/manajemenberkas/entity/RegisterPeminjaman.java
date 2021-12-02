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
@Table(name = "REGISTER_PEMINJAMAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPeminjaman {

	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "ID_BERKAS")
	private String idBerkas;
	@Column(name = "id_unit")
	private String idUnit;
	@Column(name = "STATUS_APPROVAL")
	private String statusApproval;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "approved_by")
	private String approvedBy;
	@Column(name = "approved_at")
	private LocalDateTime approvedAt;
	@Column(name = "ID_SURAT_PERMINTAAN")
	private String idSuratPermintaan;
	
}
