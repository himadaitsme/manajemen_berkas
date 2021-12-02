$(document).ready(function() {
	'use strict';
	$('.npwp15').mask("99.999.999.9-999.999", {placeholder: "__.___.___.___._-___.___"});
	
	let modalRekamBerkas = new bootstrap.Modal(document.getElementById('modal-rekam-berkas'), {});
	let formRekamBerkas = document.getElementById('form-rekam-berkas');
	
	let modalUploadBerkas = new bootstrap.Modal(document.getElementById('modal-upload-berkas'), {});
	let formUploadBerkas = document.getElementById('form-upload-berkas');
	
	let TableBerkas = $('#table-berkas').DataTable( {
	    "processing" : true,    
		"destroy" : true,
		"searching": false,
		"ajax": {
            "url": window.location.origin + "/api/berkas/list",
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
			{ "data": "jenisBerkas" },
			{ "data": "masaPajak" },
			{ "data": "tahunPajak" },
			{ "data": "tanggalBerkas" },
			{ "data": "noRak" },
			{ "data": "filename" },
			{ "data": null }
        ],
		columnDefs: [
			{ 
				"targets": 6, 
				"className" : "dt-body-center",
				"render":function ( data, type, row, meta ) {
					return row.noRak + '-' + row.noTingkat + '-' + row.noKardus + '-' + row.noUrutMap;
			    }
			},
			{ 
				"targets": 7, 
				"className" : "dt-body-center",
				"render":function ( data, type, row, meta ) {
				
					if (data == null) {
						
						return '<span class="badge bg-danger">tidak tersedia</span>';
					} 
					
					return '<a href="/api/berkas/download?id='+row.id+'" class="badge bg-success" target="_blank">tersedia</a>';
			    }
			},
			{ 
				"targets": 8, 
				"render":function ( data, type, row, meta ) {
					if (row.filename == null) {
						return '<button type="button" class="btn btn-sm btn-warning upload">Upload&nbsp&nbsp<i class="fa fa-upload" ></i></button>';
					}
					return '';
			    }
			}
	    ]
    });

	$('#table-berkas tbody').on( 'click', '.upload', function () {
        var rowData = TableBerkas.row( $(this).parents('tr') ).data();
		resetForm(formUploadBerkas);	
		$("#form-upload-berkas input[name=id]").val(rowData.id);
		modalUploadBerkas.show();
   	});
		
	$('#button-filter-berkas').click(function() {
    	
		TableBerkas.ajax.reload();
	});
	
	$('#button-rekam-berkas').click(function() {
    	
		resetForm(formRekamBerkas);
		modalRekamBerkas.show();
	});
	
	let resetForm = function(form) {
		form.reset();
		$("em.error").hide();
		$(".is-valid").removeClass("is-valid");
		$(".is-invalid").removeClass("is-invalid");
		$("input[type=hidden]").val('');
	};
	$( "#form-upload-berkas" ).submit(function(event) {
	  	event.preventDefault();
		var data = new FormData();
		data.append("id", $("#form-upload-berkas input[name=id]").val());
		data.append("file", $("#form-upload-berkas input[name=file]")[0].files[0]);
		
		var url = window.location.origin + "/api/berkas/upload"
		$.ajax({
		    contentType: 'application/json',
		    data: data,
			contentType: false,
		    dataType: 'json',
		    success: function(data){
				if (data.status == 'success') {
					Swal.fire({
					  icon: 'success',
					  title: 'Berhasil',
					  text: 'Upload Berkas Berhasil!'
					}).then(function() {
					    modalUploadBerkas.hide();
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
	});
	$( "#form-rekam-berkas" ).submit(function(event) {
	  	event.preventDefault();
		var request = {};
		
		request.id = $("#form-rekam-berkas input[name=id]").val();
		request.npwp15 = $("#form-rekam-berkas input[name=npwp15]").cleanVal();
		request.namaWp = $("#form-rekam-berkas input[name=namaWp]").val();
		request.jenisBerkas = $("#form-rekam-berkas select[name=jenisBerkas]").val();
		request.namaJenisBerkas = $("#form-rekam-berkas select[name=jenisBerkas] option:selected").text();
		request.masaPajak = $("#form-rekam-berkas select[name=masaPajak]").val();
		request.tahunPajak = $("#form-rekam-berkas input[name=tahunPajak]").val();
		request.tanggalBerkas = $("#form-rekam-berkas input[name=tanggalBerkas]").val();
		request.noRak = $("#form-rekam-berkas select[name=noRak]").val();
		request.noTingkat = $("#form-rekam-berkas input[name=noTingkat]").val();
		request.noKardus = $("#form-rekam-berkas input[name=noKardus]").val();
		request.noUrutMap = $("#form-rekam-berkas input[name=noUrutMap]").val();
		for (jenisBerkas of listSemuaJenisBerkas) {
	  	
			if (jenisBerkas.kodeJenisBerkas == request.jenisBerkas) {
				
				if (jenisBerkas.masaTahunan == "T") {
				
					request.masaPajak = 0;
				}
			}	
		}
		var url = window.location.origin + "/api/berkas/simpan"
		$.ajax({
		    contentType: 'application/json',
		    data: JSON.stringify(request),
		    dataType: 'json',
		    success: function(data){
				if (data.status == 'success') {
					Swal.fire({
					  icon: 'success',
					  title: 'Berhasil',
					  text: 'Berkas berhasil tersimpan!'
					}).then(function() {
					    modalRekamBerkas.hide();
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

function changeJenisBerkas () {
	let selectedJenisBerkas = $("#form-rekam-berkas select[name=jenisBerkas]").val();
	
	for (jenisBerkas of listSemuaJenisBerkas) {
	  	
		if (jenisBerkas.kodeJenisBerkas == selectedJenisBerkas) {
			
			if (jenisBerkas.masaTahunan == "T") {
			
				$("#form-rekam-berkas select[name=masaPajak]").removeAttr('required'); 
				$("#inputMasaPajak").css('display', 'none');
				
			} else {
				$("#form-rekam-berkas select[name=masaPajak]").attr('required', '');
				$("#inputMasaPajak").css('display', '');
			}
		}	
	}
}

function changeRak () {
	let selectedRak = $("#form-rekam-berkas select[name=noRak]").val();
	
	for (rak of listSemuaRak) {
	  	
		if (rak.rak == selectedRak) {
			console.log(rak);
			$("#form-rekam-berkas input[name=noTingkat]").attr('max', rak.jumlahTingkat);
			$("#form-rekam-berkas input[name=noKardus]").attr('max', rak.jumlahKardus);
		}	
	}
}