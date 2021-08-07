package com.example.jersey.resource;

import com.example.jersey.model.User;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("jersey")
public class MyResource {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello Jersey!";
    }
    
    @GET
    @Path("{id}")
    // Caso deseja um retorno em Json basta remover o comentário dessa linha
    //@Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
    	User user = new User(10L, "My name is Test jersey", "jersey.test@email.com");
    	return user;
    }
}
