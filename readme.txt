Java 数据验证
h2. 前言
在后台开发过程中，对参数的校验成为开发环境不可缺少的一个环节。比如参数不能为null，email那么必须符合email的格式。
        如果手动进行if判断或者写正则表达式判断无意开发效率太慢，在时间、成本、质量的博弈中必然会落后。所以把校验层抽象出来是必然的结果，下面说下几种解决方案。
      　   1、struts2的valid可以通过配置xml，xml中描述规则和返回的信息，这种方式比较麻烦、开发效率低，不推荐。
           2、validation bean 是基于JSR-303标准开发出来的，使用注解方式实现，及其方便，但是这只是一个接口，
           没有具体实现.Hibernate Validator是一个hibernate独立的包，可以直接引用，他实现了validation bean同时有做了扩展，比较强大。
编辑此区域
入门： JSR303 是一套JavaBean参数校验的标准，它定义了很多常用的校验注解，我们可以直接将这些注解加在我们JavaBean的属性上面，就可以在需要校验的时候进行校验了。
常用注解如下：
Bean Validation 中内置的 constraint         
        @Null   被注释的元素必须为 null    
        @NotNull    被注释的元素必须不为 null    
        @AssertTrue     被注释的元素必须为 true    
        @AssertFalse    被注释的元素必须为 false    
        @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
        @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
        @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
        @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
        @Size(max=, min=)   被注释的元素的大小必须在指定的范围内    
        @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内    
        @Past   被注释的元素必须是一个过去的日期    
        @Future     被注释的元素必须是一个将来的日期    
        @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
        @Valid 为级联验证标记属性、方法参数或方法返回类型。
         在验证属性、方法参数或方法返回类型时，将验证在对象及其属性上定义的约束。
          这个行为是递归应用的。
       Hibernate Validator 附加的 constraint    
        @NotBlank(message =)   验证字符串非null，且长度必须大于0    
        @Email  被注释的元素必须是电子邮箱地址    
        @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内    
        @NotEmpty   被注释的集合类型的必须非空    
        @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
      Spring Validator 支持 org.springframework.validation.annotation
        @Validated   开启对 Validator的支持。
编辑此区域
框架环境构建示例：
   要使用validation验证框架需要对其api库 和其实现进行库引用。
     如：   

            <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>6.0.13.Final</version>
        </dependency>
            此库已经依赖引用了javax的validation-api 
     javax.validation库：

       <dependency>
       <groupId>javax.validation</groupId>
       <artifactId>validation-api</artifactId>
       <version>2.0.1.Final</version>
       </dependency>
     注意：  此包是validation api包 如果你单独引入了最新版的 api 有异常风险。不建议单独引入此包。
    如果你的jdk不包含javax.el 或者 报el factorybuilder异常，则是jre/lib 下的el表达式版本太低的问题，则需要引入新版本javax.el表达式库 
       如： 

        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.el</artifactId>
            <version>3.0.1-b08</version>
        </dependency>
   关于spring validation 环境构建 
        从spring4 开始spring对Bean Validation支持方法级别验证。你需要引入spring对bean validation支持的相关包
       如： 

          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
      </dependency>
编辑此区域
简易验证代码示例：
第一步： 新建bean
   /****
* 国家
***/
@Data
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "国家名字不能为空")
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

/***
 * 省份
 * ***/
@Data
public class Province {
    @NotBlank(message="省份名字不能为空。")
    private String name;

}

   

第二步： 初始化bean并验证bean
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
        bean.setName(null);
        bean.setAddress("成都shisadsa");
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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();//用javax.Validation 构造验证工厂
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);   //验证bean并返回约束验证集
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {// 获取验证信息
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }

}


验证结果： 

      INFO: HV000001: Hibernate Validator 6.0.13.Final
国家生日必须在当前时间之前
国家名字不能为空
加油啊，经济指标不达标
邮箱的格式不合法
省份名字不能为空。


编辑此区域
关于spring中使用javax.validator
第一步： 引人spring.validator 包支持 
     第二步： 可以使用传统方法对进入方法级别参数进行验证 ，但是这样会出现大量冗余代码 ，我们spring validaor 验证拆分成spring validator验证层则
     需要使用aop 技术 需要spring aop 支持。
        《开发spring 验证层步zou》
           1.  spring aop 集成依赖引入：
                spring aop集成依赖：
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
                aop 切面实现依赖： 
                <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
第三步： bean初始化：
spring web 开发可利用@RequestBody 注解对请求参数进行json封装并装配并初始化bean。利用 spring @Validated 注解 开启对 validator bean 的验证。
此验证本身就是利用了aop技术 。验证完成过程中，spirng 会将 Set<ConstraintViolation<Object>> 对象包装并转换成一个BindingResult。
并将此对象织入到拦截点的 BindingResult 引用中。
我们可以利用aop技术来获取验证信息如：
 /*******
 * 
 * 错误参数处理器
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

编辑此区域
自定义bean validation 注解验证
有时框架自带的没法满足我们的需求，这时就需要自己动手丰衣足食了，恩恩 ，这个不难，下面说下。
　　这个例子验证字符串是大写还是小写约束标注，代码如下:

　　1、枚举类型CaseMode, 来表示大写或小写模式

         public enum CaseMode {
    UPPER,
    LOWER;
}

2、定义一个CheckCase的约束标注 
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

　3、约束条件CheckCase的验证器
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


4. 可以使用验证了，打注解就好。