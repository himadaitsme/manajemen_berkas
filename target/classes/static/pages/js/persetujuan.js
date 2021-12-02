$(document).ready(function() {
	'use strict';
	$('.npwp15').mask("99.999.999.9-999.999", {placeholder: "__.___.___.___._-___.___"});
	
	let modalPersetujuanPeminjaman = new bootstrap.Modal(document.getElementById('modal-persetujuan-peminjaman'), {});
	let formPersetujuanPeminjaman = document.getElementById('form-persetujuan-peminjaman');
	
	let TableBerkas = $('#table-berkas').DataTable( {
	    "processing" : true,    
		"destroy" : true,
		"searching": false,
		"ajax": {
            "url": window.location.origin + "/api/register-peminjaman/listRegister",
            "data": function ( d ) {
                d.npwp = $('#form-cari-berkas input[name=npwp]').cleanVal();
				d.jenisBerkas = $('#form-cari-berkas select[name=jenisBerkas]').val();
				d.masaPajak = $('#form-cari-berkas select[name=masaPajak]').val();
				d.tahunPajak = $('#form-cari-berkas input[name=tahunPajak]').val();
            }
        },
        "columns": [
            { "data": "npwp15" },
            { "data": "namaWp" },
			{ "data": "namaJenisBerkas" },
			{ "data": "masaPajak" },
			{ "data": "tahunPajak" },
			{ "data": "tanggalBerkas" },
			{ "data": "idUnit" },
			{ "data": "statusApproval" },
			{ "data": null}
        ],
		columnDefs: [
			{ 
				"targets": 6,
				"render":function ( data, type, row, meta ) {
					
					let unitOrganisasi = {};
					for (unitOrganisasi of listUnitOrganisasi) {
	  	
						if (unitOrganisasi.id == data) {
							
							return unitOrganisasi.namaUnit;
						}	
					}
					return '';
			    }
			},
			{ 
				"targets": 7, 
				"className" : "dt-body-center",
				"render":function ( data, type, row, meta ) {
					
					if (data == '0') {
						
						return '<span class="badge bg-warning">Menunggu Persetujuan</span>';
					} else if (data == '1') {
					
						return '<span class="badge bg-success">Disetujui</span>';
					} else if (data == '2') {
						
						return '<span class="badge bg-danger">Ditolak</span>';
					}	
					
					return '';
			    }
			},
			{ 
				"targets": -1, 
				"className" : "dt-body-center",
				"render":function ( data, type, row, meta ) {
					
					if (row.statusApproval == '0') {
						
						return '<button type="button" class="btn btn-sm btn-success persetujuan">Proses&nbsp&nbsp<i class="fa fa-arrow-circle-right" style="color:white"></i></button>';
					}
					
					return '';
			    }
			}
	    ]
    });

	$('#table-berkas tbody').on( 'click', '.persetujuan', function () {
        var rowData = TableBerkas.row( $(this).parents('tr') ).data();
		resetForm(formPersetujuanPeminjaman);
		$("#form-persetujuan-peminjaman input[name=idRegister]").val(rowData.idRegister);
		$("#form-persetujuan-peminjaman input[name=npwp15]").val(rowData.npwp15);
		$("#form-persetujuan-peminjaman input[name=namaWp]").val(rowData.namaWp);
		$("#form-persetujuan-peminjaman select[name=jenisBerkas]").val(rowData.jenisBerkas);
		$("#form-persetujuan-peminjaman select[name=masaPajak]").val(rowData.masaPajak);
		$("#form-persetujuan-peminjaman input[name=tahunPajak]").val(rowData.tahunPajak);
		$("#form-persetujuan-peminjaman input[name=tanggalBerkas]").val(rowData.tanggalBerkas);
		$('.npwp15').mask('99.999.999.9-999.999');
		modalPersetujuanPeminjaman.show();
   	});

	$('#button-filter-berkas').click(function() {
    	
		TableBerkas.ajax.reload();
	});
	
	let resetForm = function(form) {
		form.reset();
		$("em.error").hide();
		$(".is-valid").removeClass("is-valid");
		$(".is-invalid").removeClass("is-invalid");
		$("input[type=hidden]").val('');
	};
	
	var persetujuanPeminjaman = function(persetujuan) {
	
		var request = {};
		request.idRegister = $("#form-persetujuan-peminjaman input[name=idRegister]").val();
		request.persetujuan = persetujuan;
		
		var url = window.location.origin + "/api/register-peminjaman/persetujuan"
		$.ajax({
		    contentType: 'application/json',
		    data: JSON.stringify(request),
		    dataType: 'json',
		    success: function(data){
				if (data.status == 'success') {
					Swal.fire({
					  icon: 'success',
					  title: 'Berhasil',
					  text: 'Persetujuan Peminjaman berhasil tersimpan!'
					}).then(function() {
					    modalPersetujuanPeminjaman.hide();
						TableBerkas.ajax.reload();
					});
				} else {
					Swal.fire({
					  icon: 'error',
					  title: 'Gagal',
					  text: data.message
					});
				}
		    },
		    error: function(error){
				var title = 'Error ' + error.status;
		        Swal.fire({
				  icon: 'error',
				  title: title,
				  text: 'Terjadi Kesalahan Pada Sistem',
				  footer: 'Silahkan coba beberapa saat lagi!'
				})
		    },
		    processData: false,
		    type: 'POST',
		    url: url
		});
	};
	
	$( "#button-setuju-peminjaman").click(function(event) {
	  	event.preventDefault();
		persetujuanPeminjaman(true);
	});
	
	$( "#button-tolak-peminjaman").click(function(event) {
	  	event.preventDefault();
		persetujuanPeminjaman(false);
	});
});

function searchNpwp () {

	let npwp15 = $("#npwp15").cleanVal();
	
	if (npwp15.length == 15) {
		
		$("#namaWp").val('');
	
		$('#modal-rekam-berkas').block({ 
		    message: '<h1>Loading...</h1>', 
		    css: { border: '0px solid #000' } 
		});
	
		var url = window.location.origin + "/master-file?npwp15="+$("#npwp15").cleanVal();
		
		$.ajax({
		    contentType: 'application/json',
		    success: function(data){
				if (data.status == 'success') {
					Swal.fire({
					  icon: 'success',
					  title: 'Berhasil',
					  text: 'Data Ditemukan!'
					}).then(function() {
						$("#namaWp").val(data.data.namaWp);
					});
				} else {
					Swal.fire({
					  icon: 'error',
					  title: 'Gagal',
					  text: data.message
					});
				}
		    },
		    error: function(error){
				var title = 'Error ' + error.status;
		        Swal.fire({
				  icon: 'error',
				  title: title,
				  text: 'Terjadi Kesalahan Pada Sistem',
				  footer: 'Silahkan coba beberapa saat lagi!'
				})
		    },
			complete:function() {
			
				$('#modal-rekam-berkas').unblock();
			},
		    processData: false,
		    type: 'GET',
		    url: url
		});
	} else {
		$("#npwp15").val('');
		
		Swal.fire({
		  icon: 'error',
		  title: 'Gagal',
		  text: 'Penulisan NPWP tidak valid'
		});
	}
	
	
}
