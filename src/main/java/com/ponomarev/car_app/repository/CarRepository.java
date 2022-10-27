package com.ponomarev.car_app.repository;

import com.ponomarev.car_app.model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements CrudCarRepository {
    private final File file;

    public CarRepository(File file) {
        this.file = file;
    }


    @Override
    public List<Car> findAll() {
        List<Car> carList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(";");

                int id = Integer.parseInt(cols[0]);
                String brand = cols[1];
                int horsePower = Integer.parseInt(cols[2]);
                int cost = Integer.parseInt(cols[3]);

                carList.add(new Car(id, brand, horsePower, cost));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return carList;
    }

    @Override
    public Car save(Car car) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file, true))) {
            String carStr = car.getId() + ";"
                    + car.getBrand() + ";"
                    + car.getHorsePower() + ";"
                    + car.getCost() + ";";
            br.append(carStr);
            br.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public List<Car> findByBrand(String brand) {
        List<Car> all = findAll();
        List<Car> brandList = new ArrayList<>();
        for (Car car : all) {
            if (car.getBrand().equals(brand)) {
                brandList.add(car);
            }
        }
        return brandList;
    }

    @Override
    public void deleteAll() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        List<Car> all = findAll();
        all.removeIf(check -> check.getId() == id);
        saveAll(all);
    }

    @Override
    public void delete(Car car) {
        List<Car> all = findAll();
        all.removeIf(check -> check.equals(car));
        saveAll(all);
    }

    @Override
    public boolean existsById(int id) {
        List<Car> all = findAll();

        for (var car : all) {
            if (car.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Car update(Car car) throws IllegalArgumentException {
        List<Car> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            Car check = all.get(i);
            if (check.equals(car)) {
                all.set(i, car);
                saveAll(all);
                return car;
            }
        }
        throw new IllegalArgumentException("cant find user by entity: " + car);
    }

    @Override
    public List<Car> sortFromMinToMaxCost() {
        List<Car> all = findAll();

        all.sort(Car.COMPARE_BY_COUNT);
        return all;
    }

    @Override
    public List<Car> sortFromMaxToMinCost() {
        List<Car> all = findAll();

        all.sort(Car.COMPARE_BY_COUNT.reversed());
        return all;
    }

    public void saveAll(List<Car> carList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Car car : carList) {
                String userStr = car.getId() + ";" + car.getBrand()
                        + ";" + car.getHorsePower() + ";" + car.getCost() + ";";
                bw.write(userStr);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
