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
//import www.wcy.wat.edu.gorky.validators.PasswordsNotEqualValidator;
// 
//@Target( { TYPE, ANNOTATION_TYPE })
//@Retention(RUNTIME)
//@Constraint(validatedBy = PasswordsNotEqualValidator.class)
//@Documented
//public @interface PasswordsNotEqual {
// 
//    String message() default "PasswordsNotEqual";
// 
//    Class<?>[] groups() default {};
// 
//    Class<? extends Payload>[] payload() default {};
// 
//    String passwordFieldName() default "";
// 
//    String passwordVerificationFieldName() default "";
//}