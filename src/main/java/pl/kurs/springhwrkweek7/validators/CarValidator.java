package pl.kurs.springhwrkweek7.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.kurs.springhwrkweek7.model.Car;

import java.time.Year;

public class CarValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Car.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Car car = (Car) o;

        ValidationUtils.rejectIfEmpty(errors, "model", "error.empty");
        ValidationUtils.rejectIfEmpty(errors, "mark", "error.empty");

        if( car.getProductionYear() < 1900 ){
            errors.rejectValue("productionYear","error.year.low");
        }
        if( car.getProductionYear() > Year.now().getValue() ){
            errors.rejectValue("productionYear","error.year.high");
        }

    }
}
