package br.fiap.spg30.backend.resources;

import br.fiap.spg30.backend.entities.Ponto;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/backend/ponto")
@ApplicationScoped
public class PontoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response get() {
        return Response.ok(new Ponto()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response post(Ponto ponto) {
        return Response.ok(ponto).build();
    }
}
