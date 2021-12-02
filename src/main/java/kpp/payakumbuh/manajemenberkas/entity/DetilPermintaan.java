package kpp.payakumbuh.manajemenberkas.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DETIL_PERMINTAAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetilPermintaan {

	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "ID_PERMINTAAN")
	private String idPermintaan;
	@Column(name = "NPWP15")
	private String npwp15;
	@Column(name = "TAHUN_PAJAK")
	private Integer tahunPajak;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
}
