/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pahana_edu_backend.resources;

import Utils.item_DAO;
import classes.item;
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
@Path("item")
public class item_API {
    private final item_DAO dao = new item_DAO();
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<item> items = dao.getAllItems();
        return Response
                .ok(gson.toJson(items))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") int id) {
        item item = dao.getItemById(id);
        if (item != null) {
            return Response
                    .ok(gson.toJson(item))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Item with ID " + id + " not found\"}")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(item item) {
        boolean result = dao.addItem(item);
        if (result) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("{\"message\": \"Item added successfully\"}")
                    .build();
        } else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to add item\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id") int id, item item) {
        item.setItem_id(id);
        boolean result = dao.updateItem(item);
        if (result) {
            return Response
                    .ok("{\"message\": \"Item updated successfully\"}")
                    .build();
        } else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to update item\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") int id) {
        boolean result = dao.deleteItem(id);
        if (result) {
            return Response
                    .ok("{\"message\": \"Item deleted successfully\"}")
                    .build();
        } else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to delete item\"}")
                    .build();
        }
    }
    
   

}
