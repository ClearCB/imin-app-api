package clearcb.imin.BusinessApi.common.infrastructure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDCustomValidator implements ConstraintValidator<UUIDConstraint, String> {

    private String message;

    @Override
    public void initialize(UUIDConstraint constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // Check if the value is null or empty
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Attempt to parse the value as a UUID
        try {

            UUID uuid = UUID.fromString(value);
            return true; // If parsing succeeds, it's a valid UUID

        } catch (IllegalArgumentException e) {

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

            return false;
        }
    }
}
