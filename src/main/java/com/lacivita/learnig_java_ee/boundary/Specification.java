package com.lacivita.learnig_java_ee.boundary;

import com.lacivita.learnig_java_ee.entity.Color;
import com.lacivita.learnig_java_ee.entity.EngineType;
import com.sun.istack.internal.NotNull;

public class Specification{

    @NotNull
    private final Color color;

    @NotNull
    private final EngineType engineType;

    public Specification(Color color, EngineType engineType){
        this.color = color;
        this.engineType = engineType;
    }

    public Color getColor() {
        return color;
    }

    public EngineType getEngineType() {
        return engineType;
    }
}
