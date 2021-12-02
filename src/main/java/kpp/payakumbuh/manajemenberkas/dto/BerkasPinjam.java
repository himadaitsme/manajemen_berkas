package kpp.payakumbuh.manajemenberkas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BerkasPinjam {
	
	public static final String selectQuery = "db.ID as idBerkas, db.NPWP15 as npwp15, db.NAMA_WP as namaWp, db.JENIS_BERKAS as jenisBerkas, db.NAMA_JENIS_BERKAS as namaJenisBerkas, db.MASA_PAJAK as masaPajak, db.TAHUN_PAJAK as tahunPajak, db.TANGGAL_BERKAS as tanggalBerkas, db.NO_RAK as noRak, db.NO_Tingkat as noTingkat, db.NO_KARDUS as noKardus, db.NO_URUT_MAP as noUrutMap, db.FILENAME as filename, db.FILESIZE as filesize, rp.ID idRegister, rp.STATUS_APPROVAL as statusApproval, rp.ID_UNIT as idUnit";
	String getIdBerkas();
	String getNpwp15();
	String getNamaWp();
	String getJenisBerkas();
	String getNamaJenisBerkas();
	Integer getMasaPajak();
	Integer getTahunPajak();
	LocalDate getTanggalBerkas();
	String getNoRak();
	String getNoTingkat();
	String getNoKardus();
	String getNoUrutMap();
	String getFilename();
	BigDecimal getFilesize();
	String getIdRegister();
	String getIdUnit();
	String getStatusApproval();
}