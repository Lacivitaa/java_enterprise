package com.lacivita.learnig_java_ee.control;

import com.lacivita.learnig_java_ee.entity.Color;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class DefaultColorExposer {

    @Produces
    @Diesel
    public Color exposedDefaultColor(){
        // ...
        return Color.RED;
    }

}
