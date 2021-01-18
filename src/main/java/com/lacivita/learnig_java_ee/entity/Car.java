package com.lacivita.learnig_java_ee.entity;

import com.lacivita.learnig_java_ee.entity.Car.*;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static com.lacivita.learnig_java_ee.entity.Car.FIND_ALL;

@Entity
@Table(name = "cars")
@NamedQuery(name = FIND_ALL , query = "select c from Car c")
public class Car {

    public static final String FIND_ALL = "Car.findAll" ;

    @JsonbTransient
    @Id
    private String identifier;

    @Enumerated(EnumType.STRING)
    private Color color;

    @JsonbProperty("Engine")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car", nullable = false)
    private Set<Seat> seats = new HashSet<>();

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Set<Seat> getSeats(){
        return seats;
    }

    @Override
    public String toString(){
        return "Car{"+
                "Identifier: " + identifier + '\'' +
                ", Color: " +  color +
                ", EngineType: " + engineType + '}';
    }
}
