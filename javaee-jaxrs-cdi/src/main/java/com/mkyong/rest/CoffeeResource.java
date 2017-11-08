package com.mkyong.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

// See http://www.mkyong.com/tutorials/jax-rs-tutorials/ for more jax-rs tutorials
@ApplicationScoped
@Path("/api/coffee")
public class CoffeeResource {

    @Inject
    private CoffeeDAO coffeeDAO;

    @GET
	@Path("/names/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getProductInJSON() {
        List<String> coffeeNames = coffeeDAO.listCoffeeNames();
        return ok(coffeeNames).build();
	}

    @GET @Path("/suppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showCoffeeSuppliers(){
        List<Supplier> suppliers = coffeeDAO.showCoffeeSuppliers();
        return ok(suppliers).build();
    }

    @GET @Path("/supplier/{coffeeName}")
    public Response getSupplier(@PathParam("coffeeName") String coffeeName){
        String supplier = coffeeDAO.getSupplier(coffeeName);
        if (supplier != null){
            return ok(supplier).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found!").build();
        }
    }

    @POST @Path("/raisePrice")
    public Response raisePrice(@QueryParam("coffeeName") String coffeeName,
                                 @QueryParam("maximumPercentage") float maximumPercentage,
                                 @QueryParam("newInput") BigDecimal newInput){
        return ok(coffeeDAO.raisePrice(coffeeName, maximumPercentage, newInput)).build();
    }
}