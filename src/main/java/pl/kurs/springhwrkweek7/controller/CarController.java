package pl.kurs.springhwrkweek7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kurs.springhwrkweek7.dao.CarDao;
import pl.kurs.springhwrkweek7.model.Car;
import pl.kurs.springhwrkweek7.model.PageParam;
import pl.kurs.springhwrkweek7.validators.CarValidator;

import java.util.List;

@Controller
public class CarController {

    private CarDao carDao;

    @Autowired
    public CarController(CarDao carDao) {
        this.carDao = carDao;
    }

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/add-car")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());

        return "addCar";
    }

    @GetMapping("/car-list")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carDao.getAllCars());

        return "carList";
    }

    @GetMapping("/find-cars")
    public String searchCarsByYears(Model model) {
        model.addAttribute("pageParam", new PageParam());

        return "filteredCarList";
    }

    @GetMapping("/get-cars-production-years")
    public String getCarsByYears(@ModelAttribute("pageParam") PageParam param, Model model) {

        List<Car> cars = carDao.getCarsByProductionPeriod(param.getYearFrom(), param.getYearTo());
        model.addAttribute("cars", cars);

        return "carList";
    }

    @PostMapping("/save-new-car")
    public String saveNewCar(@ModelAttribute Car car, BindingResult result) {

        new CarValidator().validate(car, result);

        if (result.hasErrors()) {
            return "addCar";
        }

        carDao.createCar(car);

        return "redirect:/car-list";
    }

}
