/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.boundary;

import it.tss.blog.blog.control.ArticleStore;
import it.tss.blog.blog.control.CommentStore;
import it.tss.blog.blog.control.UserStore;
import it.tss.blog.blog.entity.Article;
import it.tss.blog.blog.entity.Comment;
import it.tss.blog.blog.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
//import org.hibernate.boot.cfgxml.spi.MappingReference;

/**
 *
 * @author claudio
 */
@DenyAll
@Path("/art")
public class ArticleRes {
    
    @Inject
    ArticleStore artstore;
    
    @Inject
    private CommentStore commstore;
    
    private Long id;
    private Long articleId;
     
     
    @RolesAllowed({"ADMIN"})     
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "create article (ADMIN)")
    public Response create( Article a) {
        System.out.println("sono in create" + a.toString());
        //u.setId(id);
        Article saved = artstore.create(a);
        System.out.println(saved.toString());
        return Response.status(Response.Status.CREATED)
                .entity(saved)
                .build();
    }
    
    @PermitAll
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "find by article id (permitall)")
    public Article find(@PathParam("id") Long id) {
        return artstore.find(id).orElseThrow(() -> new NotFoundException());
    }
    
    
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list articles (permitall)")
    public List<Article> all() {
        return artstore.allarticle();
    }

    @PermitAll
    @GET
    @Path("{artid}/comm")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list commet by article (permitall)")
    public JsonArray searchCommentsInArticle(@PathParam("artid") Long articleId){
    //public List<Comment> searchCommentsInArticle(@PathParam("artid") Long articleId){
        List<Comment> comm = commstore.commentsByArticle(articleId);
        
        //JsonArray build = Json.createArrayBuilder(comm.stream().map(i -> i.toJson()).collect(Collectors.toList()) ).build();
        JsonArrayBuilder createArrayBuilder = Json.createArrayBuilder();
        
        comm.stream().map(i -> i.toJson()).forEach(x -> createArrayBuilder.add(x));
        
        return createArrayBuilder.build();
        //return comm;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    /*
    get/set
     */
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
    
}


