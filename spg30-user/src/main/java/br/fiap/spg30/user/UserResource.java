package br.fiap.spg30.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/user")
@ApplicationScoped
public class UserResource {

    @Inject
    JwtService jwtService;

    @GET
    @Path("/login/token")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJwt() {
        return Response.ok(jwtService.generate(Set.of("USER"))).build();
    }
}
