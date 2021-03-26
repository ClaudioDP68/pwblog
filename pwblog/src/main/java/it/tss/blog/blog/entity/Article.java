/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author claudio
 */

@Entity
@Table(name = "article")
public class Article extends AbstractEntity implements Serializable {
    
    
    @Id
    protected Long id;
    
    private String title;
    
    private String content;
    
    private LocalDateTime dateArt;
    
    private String tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateArt() {
        return dateArt;
    }

    public void setDateArt(LocalDateTime dateArt) {
        this.dateArt = dateArt;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    
}
