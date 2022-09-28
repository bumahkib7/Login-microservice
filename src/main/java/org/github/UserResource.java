package org.github;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.Cacheable;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Cacheable
@Slf4j
public class UserResource {


    @Inject
    // Creating a new instance of the UserService class.
    UserService userService;

    @Inject
    UserRepo userRepo;


    /**
     * It takes a JSON object, converts it to a UserDTO object, then converts that to a User object, and returns a JSON
     * object
     *
     * @param userDTO This is the object that will be sent to the server.
     * @return Response object
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewUser(UserDTO userDTO) {
        var user = userService.createNewUser(userDTO);
        return Response.ok(user).build();
    }


    // This is a GET request that takes in a path parameter called username, and uses it to find a user in the database.
    // If the user is found, it returns a 200 response with the user's data.
    @GET
    @Path("/get/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username) {
        var user = userService.findUserByUsername(username);
        return Response.ok(user).build();
    }


    /**
     * > This function returns a list of all users in the database
     *
     * @return A list of all users in the database.
     */
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        var users = userRepo.findAll();
        return Response.ok(users).build();
    }


    @DELETE
    @Path("deletebyId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") String id) {
        var user = userService.deleteUserByUUID(id);
        log.info("User " + user.getUsername() + " is deleted");
        return Response.ok(user).build();
    }

}
