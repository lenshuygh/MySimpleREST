package com.lens.myrest.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.lens.myrest.model.Cat;
import com.lens.myrest.repository.CatRepository;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
@Path("/cats")
public class CatEndPoint {
	
	@Inject
	private CatRepository catRepository;

	@DELETE
	@Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
	public Response deleteCat(@PathParam("id") Long id){
		Cat cat = catRepository.find(id);
		
		if(cat == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		catRepository.delete(id);
		return Response.ok(cat).build();
	}
	
	@GET
	@Produces(APPLICATION_JSON)
	public Response getCats() {
		List<Cat> cats = catRepository.findAll();
		
		if (cats.size() == 0) {
			return Response.noContent().build();
		}
		return Response.ok(cats).build();
	}
	
	@GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getCat(@PathParam("id") @Min(1) Long id) {
        Cat book = catRepository.find(id);

        if(book == null){
        	return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }
	
	@POST
	@Consumes(APPLICATION_JSON)
	public Response createCat(Cat cat, @Context UriInfo uriInfo) {
		cat = catRepository.create(cat);
		URI createURI = uriInfo.getBaseUriBuilder().path(cat.getId().toString()).build();
		return Response.created(createURI).build();
	}
	 
	@GET
	@Path("/count")
	@Produces(APPLICATION_JSON)
	public Response countCats(){
		Long count = catRepository.countAll();
		return Response.ok(count, APPLICATION_JSON).build();
		
	}
	
}
