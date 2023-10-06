package com.example.demo.bean;

import java.util.ArrayList;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.springframework.stereotype.Component;

import com.example.demo.model.User;


@Component
public class BeanValidator {
    public Validator getValidator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    public ArrayList<String> userValidate(User user){
        ArrayList<String> arrayList = new ArrayList<>();

        Set<ConstraintViolation<User>> constraintViolations = getValidator().validate(user);

        for (ConstraintViolation<User> constraintViolation : constraintViolations) {
            if (constraintViolation.getPropertyPath().toString().equals("name")){
                arrayList.add(constraintViolation.getMessage());

            }
            if (constraintViolation.getPropertyPath().toString().equals("email")){
                arrayList.add(constraintViolation.getMessage());

            }
            if (constraintViolation.getPropertyPath().toString().equals("mobileNum")){
                arrayList.add(constraintViolation.getMessage());

            }
            if (constraintViolation.getPropertyPath().toString().equals("password")){
                arrayList.add(constraintViolation.getMessage());

            }

        }
        return arrayList;

    }   
}
