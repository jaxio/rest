package fr.jaxio.service;

import javax.activation.MimeType;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author Saravana P Shanmugam
 * 
 */
@Api(value = "/glou", description = "Operations about pets")
@Component
@Path("/glou.json")
@Scope("singleton")
public class NumberService {

	@ApiOperation(value = "add", notes = "adding stuff", responseClass = "java.lang.String")
	@GET
	@Path("/add/{numberA}/{numberB}")
	public Response add(@PathParam("numberA") int numberA, @PathParam("numberB") int numberB) {
		ResponseBuilder responseBuilder = Response.ok();
		responseBuilder.entity(new Integer(numberA + numberB));
		return responseBuilder.build();
	}

	@GET
	@Path("/echo/{id}")
	@ApiOperation(value = "echo", notes = "echo stuff", responseClass = "java.lang.String", multiValueResponse = true)
	@ApiErrors(value = { @ApiError(code = 400, reason = "Invalid status value") })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response echo(@PathParam("id") String id) {
		ResponseBuilder responseBuilder = Response.ok();
		responseBuilder.entity("have " + id);
		return responseBuilder.build();
	}

}
