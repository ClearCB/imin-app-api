package clearcb.imin.BusinessApi.common.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UUIDCustomValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UUIDConstraint {
    String message() default "Invalid UUID format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}