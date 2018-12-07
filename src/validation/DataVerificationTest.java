package validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class DataVerificationTest {

	public static void main(String[] args) {
		// 1. 获取bean
		Country xiaoming = getBean();
		// 2   验证
		List<String> validate = validate(xiaoming);
		validate.forEach(row -> {
			System.out.println(row.toString());
		});

	}

	private static Country getBean() {
		Country bean = new Country();
		bean.setName("中华人民共和国");
		bean.setAddress("成都市天府五街");
		bean.setBirthday(new Date());
		bean.setSocialism(false);;
		bean.setIndex(new BigDecimal(30));
		bean.setEmail("770265063qq.com");
		Province province = new Province();
		province.setName(null);
		ArrayList<Province> provinces = new ArrayList<>();
		bean.setList(provinces);
		provinces.add(province);
		
		return bean;
	}

	public static <T> List<String> validate(T t) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		List<String> messageList = new ArrayList<>();
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			messageList.add(constraintViolation.getMessage());
		}
		return messageList;
	}

}
