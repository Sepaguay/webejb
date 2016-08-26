/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Uztrol;
import entidades.Uztsist;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sandra
 */
@Stateless
public class UztrolFacade extends AbstractFacade<Uztrol> {

    @PersistenceContext(unitName = "EJBWEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UztrolFacade() {
        super(Uztrol.class);
    }

    //MÉTODO QUE DEVUELVE TODOS LOS ROLES
    public List<Uztrol> findAllRoles() {
        try {
            return em.createNamedQuery("Uztrol.findAll").getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    ///MÉTODO PARA ENCONTRAR ROL POR ID
    public Uztrol findRolById(BigDecimal uztrolId) {
        try {
            return (Uztrol) em.createNamedQuery("Uztrol.findByUztrolId").setParameter("uztrolId", uztrolId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER ROL POR NOMBRE
    public List<Uztrol> findRolByNombre(String uztrolNombre) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrol u WHERE UPPER(u.uztrolNombre) LIKE UPPER(:uztrolNombre)");
            query.setParameter("uztrolNombre", "%" + uztrolNombre + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER ROL POR ESTADO 
    public List<Uztrol> findRolByEstado(Character uztrolEstado) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrol u WHERE UPPER(u.uztrolEstado) LIKE UPPER(:uztrolEstado)");
            query.setParameter("uztrolEstado", "%" + uztrolEstado + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    //MÉTODO PARA OBTENER ROLES  POR ID DEL SISTEMA  
    public List<Uztrol> findRolByIdSistema(BigDecimal uztsistId) {
        try {
            Query query = em.createQuery("SELECT u FROM Uztrol u WHERE u.uztsistId = :uztsistId");
            query.setParameter("uztsistId", uztsistId);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
