package com.jrp.pma.validators;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Entered validation method..");
       // if (empRepo == null) return false;
        Employee emp = empRepo.findByEmail(value);

        if(emp != null)
            return false;
        else
            return true;
    }

}