package com.lens.myrest.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
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
    /*@ApiOperation(value = "Returns a book given an id",response = Book.class)
    @ApiResponses({
            @ApiResponse(code=200,message = "Book found"),
            @ApiResponse(code=400,message = "Invalid id"),
            @ApiResponse(code=404,message = "Book not found")
    })*/
    public Response getCat(@PathParam("id") @Min(1) Long id) {
        Cat book = catRepository.find(id);

        if(book == null){
            Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }
	
	
	/*
	 * @POST
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Creates a book given a JSon book representation")
    @ApiResponses({
            @ApiResponse(code=201,message = "The book is created"),
            @ApiResponse(code=415,message = "Format is not JSon")
    })
    public Response createBook(@ApiParam(value = "Book to be created", required = true) Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }*/
	
	@POST
	@Consumes(APPLICATION_JSON)
	public Response createCat(Cat cat, @Context UriInfo uriInfo) {
		cat = catRepository.create(cat);
		URI createURI = uriInfo.getBaseUriBuilder().path(cat.getId().toString()).build();
		return Response.created(createURI).build();
	}
	 
	
}
