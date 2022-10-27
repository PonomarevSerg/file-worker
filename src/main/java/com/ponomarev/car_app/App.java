package com.ponomarev.car_app;

import com.ponomarev.car_app.model.Car;
import com.ponomarev.car_app.repository.CarRepository;
import com.ponomarev.car_app.service.CarService;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        File file = new File("cars.csv");

        if (!file.exists()) {
            file.createNewFile();
        }
        CarRepository carRepository = new CarRepository(file);
        CarService carService = new CarService(carRepository);

//        carService.save(new Car(1, "BMW", 190, 10000));
//        carService.save(new Car(2, "BMW", 249, 20000));
//        carService.save(new Car(3, "Audi", 150, 12000));
//        carService.save(new Car(4, "Vaz", 75, 5000));
//        carService.save(new Car(5, "Vaz", 80, 6000));
//        carService.save(new Car(6, "Audi", 280, 20000));
//        carService.save(new Car(7, "Ford", 180, 9000));
//        carService.save(new Car(8, "Audi", 230, 15000));
//        carService.save(new Car(9, "Ford", 350, 25000));
//        carService.save(new Car(10, "BMW", 500, 40000));

        //System.out.println(carService.findByBrand("BMW"));

        carService.save(new Car(10, "BMW", 1000, 40000));


        System.out.println(carService.findAll());
//        System.out.println(carService.sortFromMinToMaxCost());
//        System.out.println(carService.sortFromMaxToMinCost());
    }
}
