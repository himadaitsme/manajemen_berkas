package kpp.payakumbuh.manajemenberkas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {

	private String status;
	private String code;
	private String message;
	private Object data;
}
