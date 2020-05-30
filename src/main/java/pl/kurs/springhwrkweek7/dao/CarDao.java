package pl.kurs.springhwrkweek7.dao;

import pl.kurs.springhwrkweek7.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> getAllCars();

    List<Car> getCarsByProductionPeriod(int productionFrom, int productionTo);

    void createCar(Car car);


}
