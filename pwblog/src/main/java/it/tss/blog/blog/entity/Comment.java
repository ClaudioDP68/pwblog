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
@Table(name = "comment")
public class Comment extends AbstractEntity implements Serializable {

    @Id
    protected Long id;

    private String textComment;
    private Article article;
    private User user;
    private int valutation;
    private LocalDateTime dateComment;
    private long subComment;
    
    
}
