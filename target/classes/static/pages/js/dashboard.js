$(document).ready(function() {
	'use strict';
	
	var url = window.location.origin + "/api/dashboard/tindak-lanjut?tahun=2021";
		
	$.ajax({
	    contentType: 'application/json',
	    success: function(data){
			if (data.status == 'success') {
				const config = {
				  type: 'bar',
				  data: data.data,
				  options: {
				    responsive: true,
				    plugins: {
				      legend: {
				        position: 'bottom',
				      },
				      title: {
				        display: true,
				        text: 'Monitoring Tindak Lanjut'
				      }
				    }
				  },
				};
				
				const chartPenyelesaian = new Chart(document.getElementById('chartPenyelesaian'),config);
			} else {
				
			}
	    },
	    error: function(error){
	    },
		complete:function() {
		},
	    processData: false,
	    type: 'GET',
	    url: url
	});
	
	const labelsSample = ['satu', 'dua', 'tiga', 'empat', 'lima', 'enam'];
	
	const dataSample = {
	  labels: labelsSample,
	  datasets: [
	    {
	      label: 'Open',
	      data: [10, 20, 30, 40, 50, 60],
 		  barPercentage: 0.5,
          barThickness: 30,
	      borderColor: 'rgb(255, 99, 132)',
	      backgroundColor:  'rgb(255, 99, 132)',
	    },
	    {
	      label: 'Close',
	      data: [5, 10, 15, 20, 25, 30],
 		  barPercentage: 0.5,
          barThickness: 30,
	      borderColor: 'rgb(255, 159, 64)',
	      backgroundColor: 'rgb(255, 159, 64)',
	    }
	  ]
	};

	const configSample = {
	  type: 'bar',
	  data: dataSample,
	  options: {
	    responsive: true,
	    plugins: {
	      legend: {
	        position: 'bottom',
	      },
	      title: {
	        display: true,
	        text: 'Monitoring Tindak Lanjut'
	      }
	    }
	  },
	};
	
	//const sampleChart = new Chart(document.getElementById('sampleChart'),configSample);
});