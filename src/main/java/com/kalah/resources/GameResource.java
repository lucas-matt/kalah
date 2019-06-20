package com.kalah.resources;

import com.codahale.metrics.annotation.Timed;
import com.kalah.api.ErrorResponse;
import com.kalah.api.GameResponse;
import com.kalah.core.domain.Move;
import com.kalah.core.engine.GameEngine;
import com.kalah.core.engine.preconditions.PreconditionFailException;
import com.kalah.db.GameNotFoundException;
import com.kalah.db.GameRegistry;
import com.kalah.db.GameState;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.UUID;

@Api(value = "Kalah")
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private static final Logger LOG = LoggerFactory.getLogger(GameResource.class);

    private static final String RESOURCE_PATH = "/games/";

    private final GameRegistry db;

    @Context
    private UriInfo uriInfo;

    public GameResource(GameRegistry db) {
        this.db = db;
    }

    @ApiOperation(value = "Creates a new game of kalah", response = GameResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created game"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    @POST
    @Timed
    public Response createGame() {
        GameState gameState = db.create();
        URI baseUri = uriInfo.getBaseUri().resolve(RESOURCE_PATH);
        GameResponse gameResponse = GameResponse
                .from(gameState)
                .withResourcePath(baseUri);
        LOG.info("Game created @ {}", gameResponse.getUri());
        return Response
                .created(gameResponse.getUri())
                .entity(gameResponse)
                .build();

    }

    @ApiOperation(value = "Applies a move to the existing Kalah game", response = GameResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    @PUT
    @Path("/{gameId}/pits/{pitId}")
    @Timed
    public Response move(@PathParam("gameId") UUID gameId, @PathParam("pitId") int pitId) {
        try {
            GameState nextState = executeMove(gameId, pitId);
            URI baseUri = uriInfo.getBaseUri().resolve(RESOURCE_PATH);
            GameResponse gameResponse = GameResponse
                    .fromWithStatus(nextState)
                    .withResourcePath(baseUri);
            return Response.ok(gameResponse).build();
        } catch (GameNotFoundException | PreconditionFailException e) {
            LOG.error("Error encountered handling move", e);
            ErrorResponse error = new ErrorResponse(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }
    }

    private GameState executeMove(UUID gameId, int pitId) throws GameNotFoundException, PreconditionFailException {
        GameState currentState = db.get(gameId);
        GameEngine engine = GameEngine.load(currentState);
        GameState nextState = engine.apply(new Move(pitId));
        db.put(nextState);
        LOG.info("Move completed @ pit {} for game {}", pitId, gameId);
        return nextState;
    }

}
