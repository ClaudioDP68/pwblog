/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
//import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author claudio
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    protected Long id;

    private String textComment;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "art_id")
    private Article article;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    
    private int valutation;
    
    @Column(name="dateComment", insertable=false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Schema(hidden = true)
    private LocalDateTime dateComment;
    private long subComment;

    public Comment() {
        //this.dateComment = LocalDateTime.now();
        
    }

    public Comment(String textComment, Article article, User user, int valutation ) {
        this.textComment = textComment;
        this.article = article;
        this.user = user;
        this.valutation = valutation;
        
    }

   
    public JsonObject toJson() {
        //AbstractEntity ab;
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("fname", this.user.getFname())
                .add("email", this.user.getEmail())
                .add("article", this.article.getTitle())
                .add("testo", this.textComment)
                .add("rating", this.valutation)
                .build();
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getValutation() {
        return valutation;
    }

    public void setValutation(int valutation) {
        this.valutation = valutation;
    }

    public LocalDateTime getDateComment() {
        return dateComment;
    }

    public void setDateComment(LocalDateTime dateComment) {
        this.dateComment = dateComment;
    }

    public long getSubComment() {
        return subComment;
    }

    public void setSubComment(long subComment) {
        this.subComment = subComment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", textComment=" + textComment + ", article=" + article + ", user=" + user + ", valutation=" + valutation + ", dateComment=" + dateComment + ", subComment=" + subComment + '}';
    }
    
    
}
