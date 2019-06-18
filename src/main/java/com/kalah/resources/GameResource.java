package com.kalah.resources;

import com.codahale.metrics.annotation.Timed;
import com.kalah.api.GameResponse;
import com.kalah.core.domain.Move;
import com.kalah.core.engine.GameEngine;
import com.kalah.core.engine.preconditions.PreconditionFailException;
import com.kalah.db.GameDB;
import com.kalah.db.GameNotFoundException;
import com.kalah.db.GameState;
import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.UUID;

@Api
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private static final String RESOURCE_PATH = "/games/";

    private final GameDB db;

    @Context
    private UriInfo uriInfo;

    public GameResource(GameDB db) {
        this.db = db;
    }

    @POST
    @Timed
    public Response createGame() {
        GameState gameState = db.create();
        URI baseUri = uriInfo.getBaseUri().resolve(RESOURCE_PATH);
        GameResponse gameResponse = GameResponse
                .from(gameState)
                .withResourcePath(baseUri);
        return Response
                .created(gameResponse.getUri())
                .entity(gameResponse)
                .build();

    }

    @PUT
    @Path("/{gameId}/pits/{pitId}")
    @Timed
    public GameResponse move(@PathParam("gameId") UUID gameId, @PathParam("pitId") int pitId) {
        try {
            GameState currentState = db.get(gameId);
            GameEngine engine = GameEngine.load(currentState);
            GameState nextState = engine.apply(new Move(pitId));
            GameDB.put(nextState);
        } catch (GameNotFoundException | PreconditionFailException e) {
            e.printStackTrace();
            // TODO
        }
        return null;
    }

}
