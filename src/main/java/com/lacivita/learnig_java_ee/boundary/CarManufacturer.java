package com.lacivita.learnig_java_ee.boundary;

import com.lacivita.learnig_java_ee.control.CarFactory;
import com.lacivita.learnig_java_ee.control.ProcessTrackingInterceptor;
import com.lacivita.learnig_java_ee.entity.Car;
import com.lacivita.learnig_java_ee.entity.CarCreated;

import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.ProcessInjectionTarget;
import javax.inject.Inject;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.ejb.TransactionAttribute;


@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    /*
    @Inject
    CarRepository carRepository;
     */

    @PersistenceContext//(unitName = "prod") - Just if have two or more DataSources
    EntityManager entityManager;

    @Inject
    Event<CarCreated> carCreated;

    @Interceptors(ProcessTrackingInterceptor.class)
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Car manufactureCar (Specification specs){
        Car car = carFactory.createCar(specs);
        entityManager.persist(car);
        return car;
    }

    public List<Car> retrieveCars(){
        return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
    }

    public Car retrieveCar(String identifier) {
        Car car = new Car();
        car.setIdentifier(identifier);
        return car;
    }
}
