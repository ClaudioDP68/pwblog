/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.control;

import it.tss.blog.blog.entity.Comment;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author claudio
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class CommentStore {

    @PersistenceContext
    private EntityManager em;

    public Comment create(Comment u) {

        Comment merge = em.merge(u);
        return merge;
    }

    public Optional<Comment> find(Long id) {
        Comment found = em.find(Comment.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    public List<Comment> allcomment() {
        System.out.println("sono in allArticle");
        TypedQuery<Comment> createQuery = em.createQuery("select e from Comment e", Comment.class);
        
        
        List<Comment> resultList = createQuery.getResultList();
        
        return resultList;
    }
}
