package com.lacivita.learnig_java_ee.boundary;

import com.lacivita.learnig_java_ee.entity.Car;
import com.lacivita.learnig_java_ee.entity.EngineType;
import com.sun.istack.internal.NotNull;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import javax.json.JsonArray;

@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
public class CarsResource {

    @Inject
    CarManufacturer carManufacturer;

    @Context
    UriInfo uriInfo;

    @Inject
    Validator validator;

    @GET
    public JsonArray retrieveCars(@NotNull @QueryParam("filter")EngineType engineType){
        return carManufacturer.retrieveCars().stream()
                .map(c -> Json.createObjectBuilder()
                .add("color", c.getColor().name())
                .add("engine",c.getEngineType().name())
                .add("id", c.getIdentifier())
                .add("hello", "value")
                .build())
                .collect(JsonCollectors.toJsonArray());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(@Valid @NotNull Specification specs){
        Car car = carManufacturer.manufactureCar(specs);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(CarsResource.class)
                .path(CarsResource.class, "retrieveCar")
                .build(car.getIdentifier());

        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Car retrieveCar(@PathParam("id") String identifier){
        return carManufacturer.retrieveCar(identifier);
    }

    /* One way - Custom
     public void createCar(JsonObject jsonObject){
        Color color = Color.valueOf(jsonObject.getString("color"));
        EngineType engine = EngineType.valueOf(jsonObject.getString("engine"));
        carManufacturer.manufactureCar(new Specification(color,engine));
     */
}
