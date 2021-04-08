/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.boundary;

import it.tss.blog.blog.control.UserStore;
import it.tss.blog.blog.entity.User;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

/**
 *
 * @author claudio
 */
@DenyAll
@Path("/users")
public class UserRes {
    
    @Inject
    UserStore userstore;
    
    @Context
    SecurityContext securityCtx;
    
    @Inject
    JsonWebToken jwt;
    
    private Long id;
    
    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create( User u) {
        System.out.println("sono in create" + u.toString());
        //u.setId(id);
        User saved = userstore.create(u);
        // resetto la pwd per non farla ritornare
        saved.setPwd("");
        System.out.println(saved.toString());
        return Response.status(Response.Status.CREATED)
                .entity(saved)
                .build();
    }

    
    @GET
    @SecurityRequirement(name = "jwt")
    @RolesAllowed({"ADMIN", "USER"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("users")
    public User find(@PathParam("id") Long id) {
        boolean isUserRole = securityCtx.isUserInRole(User.Role.USER.name());
        if (isUserRole && (jwt == null || jwt.getSubject() == null || Long.parseLong(jwt.getSubject()) != id)) {
            throw new ForbiddenException(Response.status(Response.Status.FORBIDDEN).entity("Access forbidden: role not allowed").build());
        }
        User user = userstore.find(id).orElseThrow(() -> new NotFoundException());
        user.setPwd("");
        return user;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("users")
    public List<User> all() {
        
        return userstore.alluser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}
