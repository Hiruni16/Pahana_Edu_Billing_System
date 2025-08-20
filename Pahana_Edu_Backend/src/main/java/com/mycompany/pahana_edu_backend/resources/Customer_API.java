/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pahana_edu_backend.resources;

import classes.customer;
import Utils.customer_DAO;
import classes.customer;
import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author User
 */
@Path ("customer")
public class Customer_API {
    
    private final customer_DAO dao = new customer_DAO();
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<customer> customers = dao.getAllCustomers();
        return Response
                .ok(gson.toJson(customers))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        customer customer = dao.getCustomerById(id);
        if (customer != null) {
            return Response
                    .ok(gson.toJson(customer))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Customer with ID " + id + " not found\"}")
                    .build();
        }
    }

    @POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addCustomer(customer customer) {
    boolean result = dao.addCustomer(customer);
    if (result) {
        return Response
                .status(Response.Status.CREATED)
                .entity("{\"message\": \"Customer added successfully\"}")
                .build();
    } else {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to add customer\"}")
                .build();
    }
}

@jakarta.ws.rs.PUT
@Path("/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updateCustomer(@PathParam("id") int id, customer customer) {
    customer.setCustomer_id(id);
    boolean result = dao.updateCustomer(customer);
    if (result) {
        return Response
                .ok("{\"message\": \"Customer updated successfully\"}")
                .build();
    } else {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to update customer\"}")
                .build();
    }
}

@jakarta.ws.rs.DELETE
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response deleteCustomer(@PathParam("id") int id) {
    boolean result = dao.deleteCustomer(id);
    if (result) {
        return Response
                .ok("{\"message\": \"Customer deleted successfully\"}")
                .build();
    } else {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to delete customer\"}")
                .build();
    }
}


}
