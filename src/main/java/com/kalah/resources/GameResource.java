package com.kalah.resources;

import com.codahale.metrics.annotation.Timed;
import com.kalah.api.GameResponse;
import com.kalah.core.domain.Game;
import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Api
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    public static final String RESOURCE_PATH = "/games/";

    @Context
    private UriInfo uriInfo;

    @POST
    @Timed
    public Response createGame() {
        Game game = Game.create();
        URI baseUri = uriInfo.getBaseUri().resolve(RESOURCE_PATH);
        GameResponse gameResponse = GameResponse
                .from(game)
                .withResourcePath(baseUri);
        return Response
                .created(gameResponse.getUri())
                .entity(gameResponse)
                .build();

    }

    @PUT
    @Path("/{gameId}/pits/{pitId}")
    @Timed
    public GameResponse move(@PathParam("gameId") String gameId, @PathParam("pitId") String pitId) {
        return null;
    }

}
