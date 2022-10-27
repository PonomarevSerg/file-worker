package com.ponomarev.car_app.service;

import com.ponomarev.car_app.model.Car;
import com.ponomarev.car_app.repository.CarRepository;

import java.util.List;

public class CarService {
    private final CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public Car save(Car car) {
        return carRepository.existsById(car.getId()) ?
                carRepository.update(car) :
                carRepository.save(car);
    }

    public boolean deleteAll() {
        carRepository.deleteAll();
        return true;
    }

    public void delete(Car car) {
        carRepository.delete(car);
    }

    public List<Car> sortFromMinToMaxCost() {
        return carRepository.sortFromMinToMaxCost();
    }

    public List<Car> sortFromMaxToMinCost() {
        return carRepository.sortFromMaxToMinCost();
    }
}
