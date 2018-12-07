package validation;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@NotBlank(message = "国家名字不能为空")
	@CheckCase(message="国家名字必须大写", value = CaseMode.UPPER)
	private String name;

	@Size(min = 6, max = 30, message = "地址应该在6-30字符之间")
	private String address;

	@DecimalMax(value = "100.00", message = "嗯，你要注意经济泡沫啊")
	@DecimalMin(value = "60.00", message = "加油啊，经济指标不达标")
	private BigDecimal index;



	@AssertFalse(message="不是社会主义")
	private boolean isSocialism;
	@Future(message = "国家生日必须在当前时间之前")
	@NotNull(message="国家生日不能为空")@Past
	private Date birthday;

	@Pattern(regexp = "^(.+)@(.+)$",message = "邮箱的格式不合法")
	private String email;
	
	@NotEmpty(message="省份集合不能为空")
	@Valid
	private List<Province> list;
	

	
	
}