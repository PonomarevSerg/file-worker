package com.ponomarev.car_app.repository;

import com.ponomarev.car_app.model.Car;
import com.ponomarev.car_app.repository.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarRepositoryTest {

    @Mock
    CarRepository carRepositoryMock = spy(new CarRepository(new File("cars.csv")));

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(carRepositoryMock);
    }

    @Test
    void findAll_shouldReturnListOfCars() {
        List<Car> carList = new ArrayList<>();
        when(carRepositoryMock.findAll()).thenReturn(carList);
        List<Car> actualList = carRepositoryMock.findAll();
        verify(carRepositoryMock).findAll();
        Assertions.assertThat(actualList).hasSameElementsAs(carList);
    }

    @Test
    void findAll_shouldThrowException() {
        CarRepository carRepositoryMockException = mock(CarRepository.class);
        RuntimeException e = new RuntimeException("File not found");
        carRepositoryMockException.findAll();
        doThrow(e).when(carRepositoryMockException).findAll();
    }

    @Test
    void save_shouldSaveCar() {
        Car carMock = spy(new Car(1, "a", 3, 4));
        Car actualCar = carRepositoryMock.save(carMock);
        verify(carRepositoryMock).save(carMock);
        assertEquals(actualCar, carMock);
    }

    @Test
    void save_shouldThrowException() {
        CarRepository carRepositoryMockException = mock(CarRepository.class);
        RuntimeException e = new RuntimeException("File not found");
        Car carMock = spy(new Car(1, "a", 3, 4));
        carRepositoryMockException.save(carMock);
        doThrow(e).when(carRepositoryMockException).findAll();
    }

    @Test
    void findByBrand() {
        List<Car> actualList = carRepositoryMock.findByBrand("123");
        when(carRepositoryMock.findByBrand("123")).thenReturn(actualList);
        verify(carRepositoryMock, times(2)).findByBrand("123");
        verify(carRepositoryMock).findAll();
    }

    @Test
    void deleteAll_shouldDelete() {
        carRepositoryMock.deleteAll();
        verify(carRepositoryMock).deleteAll();
    }

    @Test
    void deleteAll_shouldThrowException() {
        CarRepository carRepositoryMockException = mock(CarRepository.class);
        RuntimeException e = new RuntimeException("File not found");
        carRepositoryMockException.deleteAll();
        doThrow(e).when(carRepositoryMockException).deleteAll();
        assertThatThrownBy(carRepositoryMockException::deleteAll)
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteById() {
        //List<Car> carList = List.of(Mockito.spy(new Car(1,"a", 2,3)));
        carRepositoryMock.deleteById(1);
        verify(carRepositoryMock).findAll();
        verify(carRepositoryMock).deleteById(anyInt());
        verify(carRepositoryMock).saveAll(anyList());
    }

    @Test
    void delete() {
        Car carMock = mock(Car.class);
        carRepositoryMock.delete(carMock);
        verify(carRepositoryMock).findAll();
        verify(carRepositoryMock).delete(carMock);
        verify(carRepositoryMock).saveAll(anyList());
    }

    @Test
    void existsById_shouldReturnTrue() {
        when(carRepositoryMock.existsById(anyInt())).thenReturn(true);
        assertTrue(carRepositoryMock.existsById(anyInt()));
        carRepositoryMock.existsById(anyInt());
        verify(carRepositoryMock, times(3)).existsById(anyInt());
    }

    @Test
    void existsById_shouldReturnFalse() {
        when(carRepositoryMock.existsById(anyInt())).thenReturn(false);
        assertFalse(carRepositoryMock.existsById(anyInt()));
        carRepositoryMock.existsById(anyInt());
        verify(carRepositoryMock, times(3)).existsById(anyInt());
    }

    @Test
    void update_shouldUpdate() {
        //List<Car> all = List.of(mock(Car.class), mock(Car.class));
        Car carMock = mock(Car.class);
        carRepositoryMock.update(carMock);
        //List<Car> actualList = carRepositoryMock.findAll();
        when(carRepositoryMock.update(carMock)).thenReturn(carMock);
        verify(carRepositoryMock).update(carMock);
    }

    @Test
    void update_shouldThrowException() {
        //List<Car> all = List.of(mock(Car.class), mock(Car.class));
        //List<Car> actualList = carRepositoryMock.findAll();
        IllegalArgumentException e = new IllegalArgumentException("abc");
        //when(carRepositoryMock.update(any())).thenThrow(e);
        carRepositoryMock.update(mock(Car.class));
        verify(carRepositoryMock).update(mock(Car.class));
        doThrow(e).when(carRepositoryMock.update(mock(Car.class)));
    }


    @Test
    void sortFromMinToMaxCost() {
        Car carMock = mock(Car.class);
        when(carRepositoryMock.sortFromMinToMaxCost()).thenReturn(List.of(carMock));
        List<Car> actualList = carRepositoryMock.sortFromMinToMaxCost();
        verify(carRepositoryMock, times(2)).sortFromMinToMaxCost();
        assertTrue(actualList.contains(carMock));
    }

    @Test
    void sortFromMaxToMinCost() {
        Car carMock = mock(Car.class);
        when(carRepositoryMock.sortFromMaxToMinCost()).thenReturn(List.of(carMock));
        List<Car> actualList = carRepositoryMock.sortFromMaxToMinCost();
        verify(carRepositoryMock, times(2)).sortFromMaxToMinCost();
        assertTrue(actualList.contains(carMock));
    }

    @Test
    void saveAll_shouldSave() {
        List<Car> actualList = List.of(mock(Car.class));
        carRepositoryMock.saveAll(actualList);
        verify(carRepositoryMock).saveAll(actualList);
    }

    @Test
    void saveAll_shouldThrowException() {
        CarRepository carRepositoryMockException = mock(CarRepository.class);
        RuntimeException e = new RuntimeException("File not found");
        List<Car> actualList = List.of(mock(Car.class));
        carRepositoryMockException.saveAll(actualList);
        doThrow(e).when(carRepositoryMockException).saveAll(actualList);
    }
}