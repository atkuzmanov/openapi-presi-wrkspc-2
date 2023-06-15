package org.example.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Api("ExampleApi")
@Path("/example")
public interface ExampleApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all results.", response = ExampleValues
            .class, responseContainer = "List")
    List<ExampleValues> getAll(@QueryParam("qParam1") Set<String> exampleValueNames, @QueryParam("qParam2") @DefaultValue("value1") String str1);

    @GET
    @Path("{pParam1}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get one result for the given parameter.", response = ExampleValues.class)
    ExampleValues getOne(@PathParam("pParam1") String str1, @QueryParam("qParam1") @DefaultValue("value1") String str2);

    @PUT
    @Path("{pParam2}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update ExampleValues entry.", response = ExampleValues.class)
    ExampleValues updateExampleValues(@PathParam("pParam2") String str1, ExampleValues exampleValues);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create new ExampleValues entry.")
    void createExampleValues(List<ExampleValues> exampleValues);

    @DELETE
    @Path("{pParam3}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Deletes an exampleValues entries.", response = ExampleValues.class)
    void deleteExampleValuesEntries(@PathParam("pParam3") String str1);

    @DELETE
    @Path("{pParam3}/values/{pParam4}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Deletes a single exampleValues entry", response = ExampleValues.class)
    void deleteExampleValuesSingleEntry(@PathParam("pParam3") String str1, @PathParam("pParam4") String str2);
}
