package com.tyugen.WebsiteEduAPI.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A utility class for performing validation on objects.
 */
public class ValidationUtils {
    /**
     * Validates the specified object and returns a list of error messages if there are any violations.
     *
     * @param object the object to be validated
     * @param clazz  the class of the object
     * @param <T>    the type of the object
     * @return a list of error messages if there are any violations, or an empty list if there are no violations
     */
    public static <T> List<String> validate(T object, Class<T> clazz) {
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}