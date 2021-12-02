$(document).ready(function() {
	'use strict';
	$('.npwp15').mask("99.999.999.9-999.999", {placeholder: "__.___.___.___._-___.___"});
	
	let modalPinjamBerkas = new bootstrap.Modal(document.getElementById('modal-pinjam-berkas'), {});
	let formPinjamBerkas = document.getElementById('form-pinjam-berkas');
	
	let TableBerkas = $('#table-berkas').DataTable( {
	    "processing" : true,    
		"destroy" : true,
		"searching": false,
		"ajax": {
            "url": window.location.origin + "/api/register-peminjaman/list",
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
			{ "data": "statusApproval" },
			{"data" : null}
        ],
		columnDefs: [
			{ 
				"targets": 6, 
				"className" : "dt-body-center",
				"render":function ( data, type, row, meta ) {
					
					if (data == '0') {
						
						return '<span class="badge bg-warning">Menunggu Persetujuan</span>';
					} else if (data == '1') {
					
						return '<span class="badge bg-success">Disetujui</span>';
					} else if (data == '2') {
						
						return '<span class="badge bg-danger">Ditolak</span>';
					}	
					
					return '<span class="badge bg-secondary">Belum Pinjam</span>';
			    }
			},
			{ 
				"targets": -1, 
				"className" : "dt-body-center",
				"render":function ( data, type, row, meta ) {
					
					if (row.statusApproval == null || row.statusApproval == '2') {
						
						return '<button type="button" class="btn btn-sm btn-info pinjam">Pinjam&nbsp&nbsp<i class="fa fa-cart-arrow-down" style="color:black"></i></button>';
					} else if (row.statusApproval == '1') {
						
						return '<a href="/api/berkas/download?id='+row.idBerkas+'" class="btn btn-sm btn-warning download" target="_blank">Download&nbsp&nbsp<i class="fa fa-download" style="color:black"></i></a>';
					} 
					
					return '';
			    }
			}
	    ]
    });

	$('#table-berkas tbody').on( 'click', '.pinjam', function () {
        var rowData = TableBerkas.row( $(this).parents('tr') ).data();
		resetForm(formPinjamBerkas);
		$("#form-pinjam-berkas input[name=idBerkas]").val(rowData.idBerkas);
		$("#form-pinjam-berkas input[name=npwp15]").val(rowData.npwp15);
		$("#form-pinjam-berkas input[name=namaWp]").val(rowData.namaWp);
		$("#form-pinjam-berkas select[name=jenisBerkas]").val(rowData.jenisBerkas);
		$("#form-pinjam-berkas select[name=masaPajak]").val(rowData.masaPajak);
		$("#form-pinjam-berkas input[name=tahunPajak]").val(rowData.tahunPajak);
		$("#form-pinjam-berkas input[name=tanggalBerkas]").val(rowData.tanggalBerkas);
		$('.npwp15').mask('99.999.999.9-999.999');
		modalPinjamBerkas.show();
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
	
	
	$( "#form-pinjam-berkas" ).submit(function(event) {
	  	event.preventDefault();
		var request = {};
		console.log( $("#form-pinjam-berkas"));
		request.idBerkas = $("#form-pinjam-berkas input[name=idBerkas]").val();
		
		var url = window.location.origin + "/api/register-peminjaman/simpan"
		$.ajax({
		    contentType: 'application/json',
		    data: JSON.stringify(request),
		    dataType: 'json',
		    success: function(data){
				if (data.status == 'success') {
					Swal.fire({
					  icon: 'success',
					  title: 'Berhasil',
					  text: 'Peminjaman Berkas berhasil tersimpan!'
					}).then(function() {
					    modalPinjamBerkas.hide();
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
