package br.fiap.spg30.backend.resources;

import br.fiap.spg30.backend.entities.Ponto;
import br.fiap.spg30.backend.manager.PontoManager;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

@Path("/backend/ponto")
@ApplicationScoped
public class PontoResource {

    @GET
    @Path("/{userId}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response get(Long userId, LocalDate date) {
        return Response.ok(PontoManager.getByUserIdAndDate(userId, date)).build();
    }

    @GET
    @Path("/{userId}/relatorio/{startDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response getRelatorio(Long userId, LocalDate startDate, LocalDate endDate) {
        return Response.ok(PontoManager.getRelatorio(userId, startDate, endDate)).build();
    }

    @POST
    @Path("/{userId}")
    @RolesAllowed("USER")
    public Response marcarPonto(Long userId) {
        Ponto.addMarcacao(userId);
        return Response.ok().build();
    }
}
