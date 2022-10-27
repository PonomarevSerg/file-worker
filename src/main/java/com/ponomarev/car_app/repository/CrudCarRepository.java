package com.ponomarev.car_app.repository;

import com.ponomarev.car_app.model.Car;

import java.util.List;

public interface CrudCarRepository {
    Car save(Car car);

    List<Car> findAll();

    List<Car> findByBrand(String brand);

    void deleteAll();

    void deleteById(int id);

    void delete(Car car);

    boolean existsById(int id);

    Car update(Car car) throws IllegalArgumentException;

    List<Car> sortFromMinToMaxCost();

    List<Car> sortFromMaxToMinCost();
}
