package br.fiap.spg30.user.resources;

import br.fiap.spg30.user.dto.CreateUser;
import br.fiap.spg30.user.dto.LoginUser;
import br.fiap.spg30.user.entities.User;
import br.fiap.spg30.user.managers.JwtManager;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Set;

@Path("/user")
@ApplicationScoped
public class UserResource {

    @Inject
    JwtManager jwtManager;

    @POST
    @Path("/login")
    @RolesAllowed("USER")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Context SecurityContext securityContext) {
        User user = User.findByUserName(securityContext.getUserPrincipal().getName());
        return Response.ok(jwtManager.generate(Set.of(user.role))).build();
    }

    @POST
    @Path("/register")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response register(CreateUser createUser) {
        User.add(createUser.username(), createUser.email(), createUser.password(), "USER");
        return Response.ok().build();
    }

}
