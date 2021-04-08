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
import it.tss.blog.blog.entity.Commentjson;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonObject;
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
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


/**
 *
 * @author claudio
 */
@Path("/comm")
public class CommentRes {
    
    @Inject
    CommentStore commstore;
    
    @Inject
    ArticleStore artstore;
    
    @Inject
    UserStore userstore;
    
    private Long id;
    //private Long articleId;
     
    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create( Comment a) {
        System.out.println("sono in create" + a.toString());
        
        Comment saved = commstore.create(a);
        System.out.println(saved.toString());
        return Response.status(Response.Status.CREATED)
                .entity(saved)
                .build();
    }
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add comment")
    //@RequestBody(required = true, content = @Content(mediaType = "application/json", schema = @Schema(example = "{x=10,y=20}")))
    @RequestBody(required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Commentjson.class)))
    public JsonObject create(JsonObject json) {
        String testo = json.getString("testo");
        String userId = json.getString("user");
        String articleId = json.getString("article");
        String rating = json.getString("rating");
        System.out.println(testo + " " +  userId + " " +articleId + " " +rating );
        
        
        
        Article article = artstore.find(Long.parseLong(articleId)).orElseThrow(() -> new NotFoundException());
        System.out.println(article.toString());
        
        User user = userstore.find(Long.parseLong(userId)).orElseThrow(() -> new NotFoundException());
        System.out.println(user.toString());
        
        Comment comment = new Comment(testo, article, user, Integer.parseInt(rating));
        Comment comm = commstore.create(comment);
        return comm.toJson();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "get comment by id")
    public Comment find(@PathParam("id") Long id) {
        return commstore.find(id).orElseThrow(() -> new NotFoundException());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "get all comments")
    public List<Comment> all() {
        return commstore.allcomment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}
