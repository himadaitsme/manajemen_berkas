$(document).ready(function() {
	'use strict';
	
	let TableRetensi = $('#table-retensi').DataTable( {
	    "processing" : true,    
		"destroy" : true,
		"searching": false,
		"ajax": {
            "url": window.location.origin + "/api/retensi/list",
            "data": function ( d ) {
            }
        },
        "columns": [
            { "data": "npwp15" },
            { "data": "namaWp" },
			{ "data": "namaJenisBerkas" },
			{ "data": "masaPajak" },
			{ "data": "tahunPajak" },
			{ "data": "tanggalBerkas"},
			{"data" : null}
        ],
		columnDefs: [
			{ 
				"targets": -1, 
				"render":function ( data, type, row, meta ) {
					return '<button type="button" class="btn btn-sm btn-success proses">Proses&nbsp&nbsp<i class="fa fa-arrow-circle-right" style="color:white"></i></button>';
			    }
			}
	    ]
    });
});
