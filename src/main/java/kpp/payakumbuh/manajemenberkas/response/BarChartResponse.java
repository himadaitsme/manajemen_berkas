package kpp.payakumbuh.manajemenberkas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarChartResponse {

	private String[] labels;
	private BarChartDataset[] datasets;
}


