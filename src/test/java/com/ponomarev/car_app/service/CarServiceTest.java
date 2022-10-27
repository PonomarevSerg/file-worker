package com.ponomarev.car_app.service;

import com.ponomarev.car_app.model.Car;
import com.ponomarev.car_app.repository.CarRepository;
import com.ponomarev.car_app.service.CarService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarServiceTest {

    CarRepository carRepositoryMock = mock(CarRepository.class);

    private final CarService carService = new CarService(carRepositoryMock);

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(carRepositoryMock);
    }

    @Test
    void findAll() {
        List<Car> carList = List.of(mock(Car.class), mock(Car.class));

        when(carRepositoryMock.findAll()).thenReturn(carList);

        List<Car> actualList = carService.findAll();

        verify(carRepositoryMock).findAll();

        assertThat(actualList).hasSameElementsAs(carList);
    }

    @Test
    void findByBrand() {
        List<Car> carList = List.of(mock(Car.class), mock(Car.class));

        when(carRepositoryMock.findByBrand("1")).thenReturn(carList);

        List<Car> resultList = carService.findByBrand("1");

        verify(carRepositoryMock).findByBrand("1");
        assertThat(resultList).isEqualTo(carList);
    }

    @Test
    void save_shouldUpdateWhenExistById() {
        Car car = mock(Car.class);
        Car updatedCar = mock(Car.class);
        when(carRepositoryMock.existsById(anyInt())).thenReturn(true);
        when(carRepositoryMock.update(car)).thenReturn(updatedCar);

        Car resultCar = carService.save(car);

        assertThat(resultCar).isEqualTo(updatedCar);

        verify(carRepositoryMock).update(car);
        verify(carRepositoryMock).existsById(anyInt());
    }


    @Test
    void save_shouldSave() {
        Car car = mock(Car.class);
        when(carRepositoryMock.existsById(anyInt())).thenReturn(false);
        when(carRepositoryMock.save(car)).thenReturn(car);

        Car resultCar = carService.save(car);

        assertThat(resultCar).isEqualTo(car);

        verify(carRepositoryMock).existsById(anyInt());
        verify(carRepositoryMock).save(car);
    }

    @Test
    void sortFromMinToMaxCost() {
        List<Car> carList = List.of(mock(Car.class), mock(Car.class));

        when(carRepositoryMock.sortFromMinToMaxCost()).thenReturn(carList);

        List<Car> actualList = carService.sortFromMinToMaxCost();

        verify(carRepositoryMock).sortFromMinToMaxCost();
        assertThat(actualList).isEqualTo(carList);
    }

    @Test
    void sortFromMaxToMinCost() {
        List<Car> carList = List.of(mock(Car.class), mock(Car.class));

        when(carRepositoryMock.sortFromMaxToMinCost()).thenReturn(carList);

        List<Car> actualList = carService.sortFromMaxToMinCost();

        verify(carRepositoryMock).sortFromMaxToMinCost();
        assertThat(actualList).isEqualTo(carList);
    }

    @Test
    void deleteAll() {
        doNothing().when(carRepositoryMock).deleteAll();
        carService.deleteAll();
        verify(carRepositoryMock).deleteAll();
    }

    @Test
    void delete() {
        Car carMock = mock(Car.class);
        doNothing().when(carRepositoryMock).delete(carMock);
        carService.delete(carMock);
        verify(carRepositoryMock).delete(carMock);
    }
}