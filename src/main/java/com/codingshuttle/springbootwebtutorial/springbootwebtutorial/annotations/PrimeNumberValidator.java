package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation, Integer> {

    @Override
    public boolean isValid(Integer number, ConstraintValidatorContext constraintValidatorContext) {
        return isPrime(number);
    }

    public boolean isPrime(Integer number) {
        if(number < 3) return false;
        for(int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

}
