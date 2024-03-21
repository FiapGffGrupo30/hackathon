package br.fiap.spg30.user.resources;

import br.fiap.spg30.user.entities.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/admin")
public class AdminResource {

    @GET
    @Path("/users/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(User.listAll()).build();
    }
}
