/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pahana_edu_backend.resources;

import Utils.bills_DAO;
import classes.bills;
import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
@Path("bills")
public class bills_API {

    private final bills_DAO dao = new bills_DAO();
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBills() {
        List<bills> billsList = dao.getAllBills();
        return Response.ok(gson.toJson(billsList)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillById(@PathParam("id") int id) {
        bills bill = dao.getBillById(id);
        if (bill != null) {
            return Response.ok(gson.toJson(bill)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\": \"Bill with ID " + id + " not found\"}")
                           .build();
        }
    }

    @POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addBill(bills bill) {
    try {
        bills_DAO dao = new bills_DAO();
        boolean success = dao.addBill(bill);
        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\":\"Database insert failed\"}")
                           .build();
        }
    } catch (Exception e) {
        e.printStackTrace(); //  Log full stack trace
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity("{\"error\":\"" + e.getMessage() + "\"}")
                       .build();
    }
}

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBill(bills bill) {
        boolean result = dao.updateBill(bill);
        if (result) {
            return Response.ok("{\"message\": \"Bill updated successfully\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\": \"Failed to update bill\"}")
                           .build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBill(@PathParam("id") int id) {
        boolean result = dao.deleteBill(id);
        if (result) {
            return Response.ok("{\"message\": \"Bill deleted successfully\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\": \"Failed to delete bill\"}")
                           .build();
        }
    }
}
