package pl.kurs.springhwrkweek7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pl.kurs.springhwrkweek7.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        return mapResultToObject(sql);
    }

    @Override
    public List<Car> getCarsByProductionPeriod(int productionFrom, int productionTo) {
        String sql = "SELECT * FROM cars WHERE production_year BETWEEN ? and ?";
        return mapResultToObject(sql, productionFrom, productionTo);
    }

    @Override
    public void createCar(Car car) {
        String sql = "INSERT INTO cars (mark, model, color, production_year) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColor(), car.getProductionYear());
    }


    private List<Car> mapResultToObject(String sql ,@Nullable Object... args) {
        List<Car> carList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql,args);
        maps.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                Integer.parseInt(String.valueOf(element.get("production_year")))
        )));
        return carList;
    }


}
