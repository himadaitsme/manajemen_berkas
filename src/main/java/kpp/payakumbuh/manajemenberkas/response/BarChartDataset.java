package kpp.payakumbuh.manajemenberkas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarChartDataset {

	private String label;
    private Integer[] data;
    private String borderColor;
    private String backgroundColor;
    private double barPercentage;
    private double barThickness;
}


