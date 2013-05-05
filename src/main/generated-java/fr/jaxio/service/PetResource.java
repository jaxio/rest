package fr.jaxio.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.api.NotFoundException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/pet")
@Api(value = "/pet", description = "Operations about pets", listingPath="/api/pet")
public class PetResource {
	@GET
	@Path("/{petId}")
	@ApiOperation(value = "Find pet by ID", notes = "Add extra notes here", responseClass = "fr.jaxio.service.PetResource")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Invalid ID supplied"), @ApiError(code = 404, reason = "Pet not found") })
	public Response getPetById(
			@ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,5]", required = true) @PathParam("petId") String petId)
			throws NotFoundException {
		ResponseBuilder responseBuilder = Response.ok();
		responseBuilder.entity("ok");
		return responseBuilder.build();
	}
}