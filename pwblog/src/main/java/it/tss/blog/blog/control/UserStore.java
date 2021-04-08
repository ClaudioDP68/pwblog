/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.control;

import it.tss.blog.blog.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import it.tss.blog.security.control.SecurityEncoding;

/**
 *
 * @author claudio
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class UserStore {

    @PersistenceContext
    private EntityManager em;

    public User create(User u) {
        u.setPwd(SecurityEncoding.shaHash(u.getPwd()));
        User merge = em.merge(u);
        
        return merge;
    }

    public Optional<User> find(Long id) {
        User found = em.find(User.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    public List<User> alluser() {
        System.out.println("sono in alluser");
        TypedQuery<User> createQuery = em.createQuery("select e from User e", User.class);
        
        System.out.println("sono in alluser 1");
        List<User> resultList = createQuery.getResultList();
        
        return resultList;
    }
    
    public Optional<User> findByUserAndPwd(String email, String pwd) {
        try {
            User found = em.createNamedQuery(User.LOGIN, User.class)
                    .setParameter("email", email)
                    .setParameter("pwd", SecurityEncoding.shaHash(pwd))
                    .getSingleResult();
            return Optional.of(found);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

}
