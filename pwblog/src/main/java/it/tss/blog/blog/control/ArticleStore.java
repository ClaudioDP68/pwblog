/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.control;

import it.tss.blog.blog.entity.Article;
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
public class ArticleStore {

    @PersistenceContext
    private EntityManager em;

    public Article create(Article u) {

        Article merge = em.merge(u);
        return merge;
    }

    public Optional<Article> find(Long id) {
        System.out.println("find art " + id);
        Article found = em.find(Article.class, id);
        System.out.println("find art " + id);
        Optional<Article> name = found == null ? Optional.empty() : Optional.of(found);
        name.toString();
        return name;
    }

    public List<Article> allarticle() {
        System.out.println("sono in alluser");
        TypedQuery<Article> createQuery = em.createQuery("select e from Article e", Article.class);
        
        System.out.println("sono in alluser 1");
        List<Article> resultList = createQuery.getResultList();
        
        return resultList;
    }
}
