Java ������֤
h2. ǰ��
�ں�̨���������У��Բ�����У���Ϊ������������ȱ�ٵ�һ�����ڡ������������Ϊnull��email��ô�������email�ĸ�ʽ��
        ����ֶ�����if�жϻ���д������ʽ�ж����⿪��Ч��̫������ʱ�䡢�ɱ��������Ĳ����б�Ȼ��������԰�У����������Ǳ�Ȼ�Ľ��������˵�¼��ֽ��������
      ��   1��struts2��valid����ͨ������xml��xml����������ͷ��ص���Ϣ�����ַ�ʽ�Ƚ��鷳������Ч�ʵͣ����Ƽ���
           2��validation bean �ǻ���JSR-303��׼���������ģ�ʹ��ע�ⷽʽʵ�֣����䷽�㣬������ֻ��һ���ӿڣ�
           û�о���ʵ��.Hibernate Validator��һ��hibernate�����İ�������ֱ�����ã���ʵ����validation beanͬʱ��������չ���Ƚ�ǿ��
�༭������
���ţ� JSR303 ��һ��JavaBean����У��ı�׼���������˺ܶೣ�õ�У��ע�⣬���ǿ���ֱ�ӽ���Щע���������JavaBean���������棬�Ϳ�������ҪУ���ʱ�����У���ˡ�
����ע�����£�
Bean Validation �����õ� constraint         
        @Null   ��ע�͵�Ԫ�ر���Ϊ null    
        @NotNull    ��ע�͵�Ԫ�ر��벻Ϊ null    
        @AssertTrue     ��ע�͵�Ԫ�ر���Ϊ true    
        @AssertFalse    ��ע�͵�Ԫ�ر���Ϊ false    
        @Min(value)     ��ע�͵�Ԫ�ر�����һ�����֣���ֵ������ڵ���ָ������Сֵ    
        @Max(value)     ��ע�͵�Ԫ�ر�����һ�����֣���ֵ����С�ڵ���ָ�������ֵ    
        @DecimalMin(value)  ��ע�͵�Ԫ�ر�����һ�����֣���ֵ������ڵ���ָ������Сֵ    
        @DecimalMax(value)  ��ע�͵�Ԫ�ر�����һ�����֣���ֵ����С�ڵ���ָ�������ֵ    
        @Size(max=, min=)   ��ע�͵�Ԫ�صĴ�С������ָ���ķ�Χ��    
        @Digits (integer, fraction)     ��ע�͵�Ԫ�ر�����һ�����֣���ֵ�����ڿɽ��ܵķ�Χ��    
        @Past   ��ע�͵�Ԫ�ر�����һ����ȥ������    
        @Future     ��ע�͵�Ԫ�ر�����һ������������    
        @Pattern(regex=,flag=)  ��ע�͵�Ԫ�ر������ָ����������ʽ
        @Valid Ϊ������֤������ԡ����������򷽷��������͡�
         ����֤���ԡ����������򷽷���������ʱ������֤�ڶ����������϶����Լ����
          �����Ϊ�ǵݹ�Ӧ�õġ�
       Hibernate Validator ���ӵ� constraint    
        @NotBlank(message =)   ��֤�ַ�����null���ҳ��ȱ������0    
        @Email  ��ע�͵�Ԫ�ر����ǵ��������ַ    
        @Length(min=,max=)  ��ע�͵��ַ����Ĵ�С������ָ���ķ�Χ��    
        @NotEmpty   ��ע�͵ļ������͵ı���ǿ�    
        @Range(min=,max=,message=)  ��ע�͵�Ԫ�ر����ں��ʵķ�Χ��
      Spring Validator ֧�� org.springframework.validation.annotation
        @Validated   ������ Validator��֧�֡�
�༭������
��ܻ�������ʾ����
   Ҫʹ��validation��֤�����Ҫ����api�� ����ʵ�ֽ��п����á�
     �磺   

            <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>6.0.13.Final</version>
        </dependency>
            �˿��Ѿ�����������javax��validation-api 
     javax.validation�⣺

       <dependency>
       <groupId>javax.validation</groupId>
       <artifactId>validation-api</artifactId>
       <version>2.0.1.Final</version>
       </dependency>
     ע�⣺  �˰���validation api�� ����㵥�����������°�� api ���쳣���ա������鵥������˰���
    ������jdk������javax.el ���� ��el factorybuilder�쳣������jre/lib �µ�el���ʽ�汾̫�͵����⣬����Ҫ�����°汾javax.el���ʽ�� 
       �磺 

        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.el</artifactId>
            <version>3.0.1-b08</version>
        </dependency>
   ����spring validation �������� 
        ��spring4 ��ʼspring��Bean Validation֧�ַ���������֤������Ҫ����spring��bean validation֧�ֵ���ذ�
       �磺 

          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
      </dependency>
�༭������
������֤����ʾ����
��һ���� �½�bean
   /****
* ����
***/
@Data
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "�������ֲ���Ϊ��")
    private String name;

    @Size(min = 6, max = 30, message = "��ַӦ����6-30�ַ�֮��")
    private String address;

    @DecimalMax(value = "100.00", message = "�ţ���Ҫע�⾭����ĭ��")
    @DecimalMin(value = "60.00", message = "���Ͱ�������ָ�겻���")
    private BigDecimal index;

    @AssertFalse(message="�����������")
    private boolean isSocialism;
    @Future(message = "�������ձ����ڵ�ǰʱ��֮ǰ")
    @NotNull(message="�������ղ���Ϊ��")@Past
    private Date birthday;

    @Pattern(regexp = "^(.+)@(.+)$",message = "����ĸ�ʽ���Ϸ�")
    private String email;

    @NotEmpty(message="ʡ�ݼ��ϲ���Ϊ��")
    @Valid
    private List<Province> list;

}       

/***
 * ʡ��
 * ***/
@Data
public class Province {
    @NotBlank(message="ʡ�����ֲ���Ϊ�ա�")
    private String name;

}

   

�ڶ����� ��ʼ��bean����֤bean
   public class DataVerificationTest {

    public static void main(String[] args) {
        // 1. ��ȡbean
        Country xiaoming = getBean();
        // 2   ��֤
        List<String> validate = validate(xiaoming);
        validate.forEach(row -> {
            System.out.println(row.toString());
        });

    }

    private static Country getBean() {
        Country bean = new Country();
        bean.setName(null);
        bean.setAddress("�ɶ�shisadsa");
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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();//��javax.Validation ������֤����
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);   //��֤bean������Լ����֤��
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {// ��ȡ��֤��Ϣ
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }

}


��֤����� 

      INFO: HV000001: Hibernate Validator 6.0.13.Final
�������ձ����ڵ�ǰʱ��֮ǰ
�������ֲ���Ϊ��
���Ͱ�������ָ�겻���
����ĸ�ʽ���Ϸ�
ʡ�����ֲ���Ϊ�ա�


�༭������
����spring��ʹ��javax.validator
��һ���� ����spring.validator ��֧�� 
     �ڶ����� ����ʹ�ô�ͳ�����Խ��뷽���������������֤ ��������������ִ���������� ������spring validaor ��֤��ֳ�spring validator��֤����
     ��Ҫʹ��aop ���� ��Ҫspring aop ֧�֡�
        ������spring ��֤�㲽zou��
           1.  spring aop �����������룺
                spring aop����������
                <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>

                <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>
                aop ����ʵ�������� 
                <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
�������� bean��ʼ����
spring web ����������@RequestBody ע��������������json��װ��װ�䲢��ʼ��bean������ spring @Validated ע�� ������ validator bean ����֤��
����֤�������������aop���� ����֤��ɹ����У�spirng �Ὣ Set<ConstraintViolation<Object>> �����װ��ת����һ��BindingResult��
�����˶���֯�뵽���ص�� BindingResult �����С�
���ǿ�������aop��������ȡ��֤��Ϣ�磺
 /*******
 * 
 * �������������
 * ********/
@Aspect
@Component
public class ParameterErrorHandler {

    @Pointcut("execution(* com.xunshi.southwest_oil.modular.system.controller.*.*(..))")
    private void cutParameterError() {

    }

    @Around("cutParameterError()")
    public Object hander(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof BindingResult) {
                BindingResult  binding =(BindingResult) args[i];
                if (binding.hasErrors()) {
                    List<String> strlist = new ArrayList<>();
                    List<ObjectError> allErrors = binding.getAllErrors();
                    for (Iterator<ObjectError> iterator = allErrors.iterator(); iterator.hasNext();) {
                        ObjectError objectError = (ObjectError) iterator.next();
                        strlist.add(objectError.getDefaultMessage());
                    }
                    return new ResponseState(ExceptionEnum.DataIsNULL.getCode(), strlist.toString());
                }
            }
        }
        return point.proceed();
    }
}

�༭������
�Զ���bean validation ע����֤
��ʱ����Դ���û���������ǵ�������ʱ����Ҫ�Լ����ַ�����ʳ�ˣ����� ��������ѣ�����˵�¡�
�������������֤�ַ����Ǵ�д����СдԼ����ע����������:

����1��ö������CaseMode, ����ʾ��д��Сдģʽ

         public enum CaseMode {
    UPPER,
    LOWER;
}

2������һ��CheckCase��Լ����ע 
  import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
public @interface CheckCase {

    String message() default "{com.mycompany.constraints.checkcase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseMode value();

}

��3��Լ������CheckCase����֤��
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;

    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {

        if (object == null)
            return true;

        if (caseMode == CaseMode.UPPER)
            return object.equals(object.toUpperCase());
        else
            return object.equals(object.toLowerCase());
    }

}  


4. ����ʹ����֤�ˣ���ע��ͺá�