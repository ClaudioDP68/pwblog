/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.boundary;

import it.tss.blog.blog.control.ArticleStore;
import it.tss.blog.blog.control.UserStore;
import it.tss.blog.blog.entity.Article;
import it.tss.blog.blog.entity.User;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.hibernate.boot.cfgxml.spi.MappingReference;

/**
 *
 * @author claudio
 */
@Path("/art")
public class ArticleRes {
    
    @Inject
    ArticleStore artstore;
    
    private Long id;
    private Long articleId;
     
     
         
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create( Article a) {
        System.out.println("sono in create" + a.toString());
        //u.setId(id);
        Article saved = artstore.create(a);
        System.out.println(saved.toString());
        return Response.status(Response.Status.CREATED)
                .entity(saved)
                .build();
    }

    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article find(@PathParam("id") Long id) {
        return artstore.find(id).orElseThrow(() -> new NotFoundException());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> all() {
        return artstore.allarticle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}


