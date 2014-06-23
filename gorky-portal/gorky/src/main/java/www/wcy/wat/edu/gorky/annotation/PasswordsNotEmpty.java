//package www.wcy.wat.edu.gorky.annotation;
//
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.ElementType.TYPE;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//
//import www.wcy.wat.edu.gorky.validators.PasswordsNotEmptyValidator;
// 
//@Target( { TYPE, ANNOTATION_TYPE })
//@Retention(RUNTIME)
//@Constraint(validatedBy = PasswordsNotEmptyValidator.class)
//@Documented
//public @interface PasswordsNotEmpty {
// 
//    String message() default "PasswordsNotEmpty";
// 
//    Class<?>[] groups() default {};
// 
//    Class<? extends Payload>[] payload() default {};
// 
//    String triggerFieldName() default "";
// 
//    String passwordFieldName() default "";
// 
//    String passwordVerificationFieldName() default "";
//}