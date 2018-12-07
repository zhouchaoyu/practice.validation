package validation;



import javax.validation.constraints.NotBlank;

import lombok.Data;

/***
 * 省份
 * ***/
@Data
public class Province {
    @NotBlank(message="省份名字不能为空。")
	private String name;
	
}
