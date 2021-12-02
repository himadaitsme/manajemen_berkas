$(document).ready(function() {
	'use strict';
	
	let modalRekamJenisBerkas = new bootstrap.Modal(document.getElementById('modal-rekam-jenis-berkas'), {});
	let formRekamJenisBerkas = document.getElementById('form-rekam-jenis-berkas');
	
	let TableJenisBerkas = $('#table-jenis-berkas').DataTable( {
	    "processing" : true,    
		"destroy" : true,
		"searching": false,
		"ajax": {
            "url": window.location.origin + "/api/jenis-berkas/list",
            "data": function ( d ) {
            }
        },
        "columns": [
            { "data": "kodeJenisBerkas" },
            { "data": "jenisBerkas" },
			{ "data": null }
        ],
		columnDefs: [
	    ]
    });

	$('#button-tambah-jenis-berkas').click(function() {
    	
		resetForm(formRekamJenisBerkas);
		modalRekamJenisBerkas.show();
	});
	
	let resetForm = function(form) {
		form.reset();
		$("em.error").hide();
		$(".is-valid").removeClass("is-valid");
		$(".is-invalid").removeClass("is-invalid");
		$("input[type=hidden]").val('');
	};

});
