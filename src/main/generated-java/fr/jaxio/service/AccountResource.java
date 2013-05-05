package fr.jaxio.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Variant;

import org.springframework.dao.DataIntegrityViolationException;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import fr.jaxio.domain.Account;
import fr.jaxio.repository.AccountRepository;
import fr.jaxio.repository.AddressRepository;

@Api(value = "/account", description = "Operations about accounts")
@Path("/account.json")
@Singleton
@Named
public class AccountResource {
	@Inject
	AccountRepository accountRepository;
	@Inject
	AddressRepository addressRepository;

	@GET
	@Path("/{id}")
	@ApiOperation(value = "get", notes = "get account by id", responseClass = "fr.jaxio.domain.Account")
	public Response get(@PathParam("id") String id) {
		ResponseBuilder responseBuilder = Response.ok();
		responseBuilder.entity(accountRepository.getById(id).copy());
		return responseBuilder.build();
	}

	@ApiOperation(value = "delete", notes = "get account by id")
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		accountRepository.deleteById(id);
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@POST
	@Path("/")
	@Consumes(APPLICATION_JSON)
	@ApiOperation(value = "create", notes = "create account", responseClass = "fr.jaxio.domain.Account")
	public Response create(@ApiParam(value = "account to be created", required = true) Account account) {
		if (account.getHomeAddressId() != null) {
			account.setHomeAddress(addressRepository.getById(account.getHomeAddressId()));
		}
		try {
			accountRepository.merge(account);
			return Response.created(URI.create("/")).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
		}
	}
}
