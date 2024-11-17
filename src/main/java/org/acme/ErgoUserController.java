package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.UserDetails;
import org.acme.service.ErgoUserService;
import java.util.List;

@Path("/ergoBuddy/users")
public class ErgoUserController {

    @Inject
    private ErgoUserService ergoUserService;

    @POST
    @Transactional
    public Response createErgoUser(UserDetails userDetails) {
        final UserDetails responseOfUserDetails = ergoUserService.createErgoUser(userDetails);
        return Response.status(Response.Status.CREATED).entity(responseOfUserDetails).build();
    }

    @GET
    public List<UserDetails> getAllErgoUsers() {
        return ergoUserService.getAllErgoUsers();
    }

    @GET
    @Path("/{gpnID}")
    @Transactional
    public Response getErgoUserById(@PathParam("gpnID") Long gpnID,UserDetails userDetails) {
        UserDetails userDetailsFinal = ergoUserService.getErgoUserById(gpnID);
        if (userDetailsFinal == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(userDetailsFinal).build();
    }

    @PUT
    @Path("/{gpnID}")
    @Transactional
    public Response updateErgoUserById(@PathParam("gpnID") Long gpnID, UserDetails userDetails) {
        UserDetails userDetailsFinal = ergoUserService.updateErgoUser(gpnID,userDetails);
        if (userDetailsFinal == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(userDetailsFinal).build();
    }

    @DELETE
    @Path("/{gpnID}")
    @Transactional
    public Response deleteUser(@PathParam("gpnID") Long gpnID) {
        boolean deleted = ergoUserService.deleteErgoUserById(gpnID);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
