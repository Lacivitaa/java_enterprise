package com.lacivita.learnig_java_ee.control;

import com.lacivita.learnig_java_ee.entity.Car;
import com.lacivita.learnig_java_ee.boundary.Specification;
import com.lacivita.learnig_java_ee.entity.Color;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Random;
import java.util.UUID;

public class CarFactory {

    @Inject
    @Diesel
    Color defaultColor;

    //@Transactional(Transactional.TxType.REQUIRED)
    public Car createCar(Specification specs){
        Car car = new Car();
        car.setIdentifier(UUID.randomUUID().toString());
        car.setColor(specs.getColor()==null ? defaultColor:specs.getColor());
        car.setEngineType(specs.getEngineType());
        return car;
    }
}