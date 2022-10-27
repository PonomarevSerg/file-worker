package com.ponomarev.car_app.model;

import java.util.Comparator;
import java.util.Objects;

public class Car {
    private final int id;
    private final String brand;
    private final int horsePower;
    private final int cost;

    public Car(int id, String brand, int horsePower, int cost) {
        this.id = id;
        this.brand = brand;
        this.horsePower = horsePower;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", horsePower=" + horsePower +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final Comparator<Car> COMPARE_BY_COUNT = (car, car1) -> car.getCost() - car1.getCost();

}
