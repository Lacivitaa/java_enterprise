package com.lacivita.learnig_java_ee.control;

import com.lacivita.learnig_java_ee.entity.Car;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CarRepository {
    List<Car> cars;
    Car car;

    public void store(Car car) {
        cars.add(car);
    }

    public  List<Car> loadCars() {
        return cars;
    }

    public  Car loadCar() {
        return car;
    }
}
