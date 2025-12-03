package com.mycompany.peluqueriacanina.persistencia;

import com.mycompany.peluqueriacanina.logica.Raza;
import com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RazaJpaController implements Serializable {

    public RazaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public RazaJpaController() {
        emf = Persistence.createEntityManagerFactory("PeluCaninaPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Raza raza) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(raza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Raza raza) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            raza = em.merge(raza);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = raza.getId_raza();
                if (findRaza(id) == null) {
                    throw new NonexistentEntityException("The raza with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
 
    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Raza raza;
            try {
                raza = em.getReference(Raza.class, id);
                raza.getId_raza();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The raza with id " + id + " no longer exists.", enfe);
            }
            em.remove(raza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Raza> findRazaEntities() {
        return findRazaEntities(true, -1, -1);
    }

    public List<Raza> findRazaEntities(int maxResults, int firstResult) {
        return findRazaEntities(false, maxResults, firstResult);
    }
    
    private List<Raza> findRazaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Raza.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Raza findRaza(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Raza.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getRazaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Raza> rt = cq.from(Raza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}